package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;

/**
 *
 */
public class ConfusionMatrixViewImpl extends Composite implements ConfusionMatrixPresenter.View {

    ConfusionMatrixPresenter presenter;

    // TODO: we should use layout panels if we can... not sure if they will work with the
    //       current DoublePanelView. This would be a good thing for a second view impl
    VerticalPanel mainFrame;

    // TODO: these are the ui elements that we have right now.... we need to figure out a good layout
    //       - data key - can we link back to the originating raw data, maybe make that tab the selected tab?
    //       - model key - useful for linking to trees, not sure if it is important to display
    //       response variable - dont know what this is...
    //       number of trees
    //       mtry - dont know what this is
    //       out of bag error estimates/full scoring - this is important to show
    //       - confusion key - useful for redisplaying this confusion matrix again
    //       confusion matrix - this will be a table/grid
    //       classification errors
    //       used/skipped rows
    //       average tree information min/mean/max for leaves and depth
    //
    // NOTE: the data will change so the view needs to be dynamic.
    //       we need to make it obvious if trees are currently being generated or if the process is finished

    public ConfusionMatrixViewImpl() { }

    public void buildUi() {
        mainFrame = new VerticalPanel();
        // TODO: figure out what makes sense for layout
        mainFrame.setSize("100%", "100%");
        mainFrame.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        mainFrame.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
        mainFrame.setSpacing(10);

        // TODO: add more widgets

        initWidget(mainFrame);
    }

    @Override
    public void setData(RFView data) {
        // TODO: update the widgets with new data...
        mainFrame.add(new HTML("<h1>TODO: Implement me!</h1>"));
        mainFrame.add(new HTML(data.toString()));
    }

    @Override
    public void setPresenter(ConfusionMatrixPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
