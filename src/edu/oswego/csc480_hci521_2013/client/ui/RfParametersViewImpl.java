package edu.oswego.csc480_hci521_2013.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.RfParametersPresenter;

/* Author: Mike Hayes
 * TODO: Try to move functional elements to presenter classes. IE: onChange(), checkSelection()
 * TODO: Confirm that H2O has a way of auto-detecting which column should be used as a 
 *      classification variable by default and implement that here.
 * TODO: Need better way to handle other default value such as number of trees. Right now it is
 *       just final global variable.
 */
public class RfParametersViewImpl extends PopupPanel implements ChangeHandler, RfParametersPresenter.View {

    private final int NTREES_DEFAULT = 50;
    private static final Logger logger = Logger.getLogger(RfParametersViewImpl.class.getName());

    RfParametersPresenter presenter;
    VerticalPanel mainFrame;
    List<String> columnHeaders; //Headers of the data columns from the data source.
    HashMap<Integer, String> ignoreRemoved; //A possibly temporarily removed item from the ignoreColumns ListBox.
    //List element for the classification variable. Is populated in the setHeaders method.
    private final ListBox classificationVariable = new ListBox();
    private final ListBox ignoreColumns = new ListBox(true);

    public RfParametersViewImpl() { }

    public void buildUi() {
        mainFrame = new VerticalPanel();
        setWidget(mainFrame);
        mainFrame.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainFrame.setVerticalAlignment(VerticalPanel.ALIGN_TOP);

        HorizontalPanel header = new HorizontalPanel();
        header.setWidth("100%");
        header.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        header.add(new Label("Random Forest Model Parameters"));

        //Create and populate a list element for the classification variable.
        HorizontalPanel classVar = new HorizontalPanel();
        classVar.add(new Label("Classification Variable: "));
        classVar.add(classificationVariable);

        //Number of trees input box
        HorizontalPanel numTrees = new HorizontalPanel();
        numTrees.add(new Label("Number of trees: "));
        TextBox numTreesVal = new TextBox();
        numTreesVal.setText(Integer.toString(NTREES_DEFAULT));
        numTrees.add(numTreesVal);

        //Create and populate the ignore columns selection list element.
        HorizontalPanel ignoreColumnsPanel = new HorizontalPanel();
        ignoreColumns.setWidth("100%");
        ignoreColumnsPanel.add(new Label("Ignore columns: "));
        ignoreColumnsPanel.add(ignoreColumns);

        //Buttons...
        HorizontalPanel buttons = new HorizontalPanel();
        buttons.add(new Button("OK"));
        buttons.add(new Button("Cancel"));

        //Add HorizontalPanels to the main VerticalPanel
        mainFrame.add(header);
        mainFrame.add(classVar);
        mainFrame.add(numTrees);
        mainFrame.add(ignoreColumnsPanel);
        mainFrame.add(buttons);

        classificationVariable.addChangeHandler(this);
    }

    //Called when classificationVariable selection is changed.
    public void onChange(ChangeEvent event){
        int selectedIndex = classificationVariable.getSelectedIndex(); 
        String selectedName = classificationVariable.getValue(selectedIndex);
        setIgnoreColumns(selectedName);
    }

    public void setHeaders(List<String> headers){
        this.columnHeaders = headers; 
        for(String column : columnHeaders){
            classificationVariable.addItem(column);
        }
        classificationVariable.setItemSelected(0,true);
        //Ignore the first header since it is selected first.
        setIgnoreColumns(columnHeaders.get(0));
    }

    //Set the available ignore columns to everything excepted the selected class var
    public void setIgnoreColumns(String selected){
        logger.log(Level.INFO, "Not setting: " + selected);
        ignoreColumns.clear();
        for(String column : columnHeaders){
            if(!column.equals(selected))
                ignoreColumns.addItem(column);
        }
        ignoreColumns.setVisibleItemCount(columnHeaders.size());
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

    @Override
    public void setPresenter(RfParametersPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
