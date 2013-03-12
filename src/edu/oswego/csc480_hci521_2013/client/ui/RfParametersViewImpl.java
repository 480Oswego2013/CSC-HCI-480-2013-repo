package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.RfParametersPresenter;

/* Author: Mike Hayes
 *
 */
public class RfParametersViewImpl extends PopupPanel implements RfParametersPresenter.View {

    RfParametersPresenter presenter;

    VerticalPanel mainFrame;

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
        ListBox columns = new ListBox();
        /*for(String column : result){
          columns.addItem(column);
          }*/
        classVar.add(columns);

        //Number of trees input box
        HorizontalPanel numTrees = new HorizontalPanel();
        numTrees.add(new Label("Number of trees: "));
        numTrees.add(new TextBox());

        //Create and populate the ignore columns selection list element.
        HorizontalPanel ignoreColumns = new HorizontalPanel();
        ListBox ignore = new ListBox();
        /*for(String column : result){
          ignore.addItem(column);
          }
          ignore.setVisibleItemCount(result.size());*/
        ignore.setWidth("100%");
        ignoreColumns.add(new Label("Ignore columns: "));
        ignoreColumns.add(ignore);

        //Buttons...
        HorizontalPanel buttons = new HorizontalPanel();
        buttons.add(new Button("OK"));
        buttons.add(new Button("Cancel"));

        //Add HorizontalPanels to the main VerticalPanel
        mainFrame.add(header);
        mainFrame.add(classVar);
        mainFrame.add(numTrees);
        mainFrame.add(ignoreColumns);
        mainFrame.add(buttons);

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
