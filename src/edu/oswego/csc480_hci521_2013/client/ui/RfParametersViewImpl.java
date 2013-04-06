package edu.oswego.csc480_hci521_2013.client.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.RfParametersPresenter;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

/**
 * @author Michael Hayes
 * TODO: Confirm that H2O has a way of auto-detecting which column should be used as a
 *      classification variable by default and implement that here.
 * TODO: Need better way to handle other default value such as number of trees.
 * TODO: Input sanity checks such as numTrees is an integer.
 */
public class RfParametersViewImpl extends PopupPanel implements RfParametersPresenter.View {

    static final Logger logger = Logger.getLogger(RfParametersPresenter.View.class.getName());

    interface Style extends CssResource {
        String error();
    }

    interface Binder extends UiBinder<Widget, RfParametersViewImpl> {}
    private static Binder uiBinder = GWT.create(Binder.class);
    private RfParametersPresenter presenter;
    private List<String> columnHeaders; //Headers of the data columns from the data source.

    @UiField ListBox classVars;
    @UiField IntegerBox numTrees;
    @UiField ListBox ignoreCols;
    @UiField Button submit;
    @UiField Button cancel;
    @UiField Label errorLabel;

    public RfParametersViewImpl() {
        setWidget(uiBinder.createAndBindUi(this));
    }

    public void buildUi() {
    }

    //Called when classVars selection is changed.
    @UiHandler("classVars")
    public void onChange(ChangeEvent event){
        int selectedIndex = classVars.getSelectedIndex();
        String selectedName = classVars.getValue(selectedIndex);
        setIgnoreColumns(selectedName);
    }

    @UiHandler("submit")
    public void onSubmitClick(ClickEvent event){
        RFBuilder builder = new RFBuilder(presenter.getDataKey());

        //TODO: Should probably alert the user instead of defaulting to 50.
        if(numTrees == null || numTrees.getValue() == null || numTrees.getValue() <= 0)
            builder.setNtree(50);
        else
            builder.setNtree(numTrees.getValue());

        int classVarSelected = classVars.getSelectedIndex();
        String classVarVal = classVars.getValue(classVarSelected);
        builder.setResponseVariable(classVarVal);
        for(int i = 0; i < ignoreCols.getItemCount(); i++){
            if(ignoreCols.isItemSelected(i)){
                Integer ignoreThis = Integer.valueOf(ignoreCols.getValue(i));
                builder.setIgnore(ignoreThis);
            }
        }
        presenter.fireRFParameterEvent(builder);
        //this.hide();
    }

    @UiHandler("cancel")
    public void onCancelClick(ClickEvent event){
        this.hide();
    }

    public void setHeaders(List<String> headers){
       this.columnHeaders = headers;
        for(String column : columnHeaders){
            classVars.addItem(column);
        }
        classVars.setItemSelected(0,true);
        //Ignore the first header since it is selected first.
        setIgnoreColumns(columnHeaders.get(0));
    }

    //Set the available ignore columns to everything excepted the selected class var
    public void setIgnoreColumns(String selected){
        ignoreCols.clear();
        //for(String column : columnHeaders){
        for(int i = 0; i < columnHeaders.size(); i++){
            String column = columnHeaders.get(i);
            if(!column.equals(selected))
                ignoreCols.addItem(column, Integer.toString(i));
        }
        ignoreCols.setVisibleItemCount(columnHeaders.size());
    }

    public void showPopUp(){
        final PopupPanel me = this;
        this.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            public void setPosition(int offsetWidth, int offsetHeight){
                int left = 100;
                int top = 140;
                me.setPopupPosition(left, top);
            }
        });
    }

    public void hidePopup(){
	    this.hide();
    }

    public void setError(String error){
	    errorLabel.setText(error);
    }

    @Override
    public void setPresenter(RfParametersPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
