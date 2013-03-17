package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEvent;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEventHandler;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.ui.PanelView;
import edu.oswego.csc480_hci521_2013.client.ui.TreePanel;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ColumnDef;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.InspectRow;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFTreeViewBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoublePanelActivity extends AbstractActivity implements PanelView.Presenter {

    static final Logger logger = Logger.getLogger(DoublePanelActivity.class.getName());
	private ClientFactory clientFactory;

	public DoublePanelActivity(DoublePanelPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		PanelView panelView = clientFactory.getDoublePanelView();
		panelView.setPresenter(this);
		panelView.buildGui();

		containerWidget.setWidget(panelView.asWidget());
        bind();
	}

	@Override
	public String mayStop() {
//		return "Please don't leave me.";
		return null;
	}

    private void bind() {
        EventBus eventbus = clientFactory.getEventBus();
        eventbus.addHandler(InspectDataEvent.TYPE, new InspectDataEventHandler() {
            @Override
            public void onViewData(InspectDataEvent e) {
                addDataTab(e.getName());
            }
        });
        eventbus.addHandler(TreeVisEvent.TYPE, new TreeVisEventHandler() {
            @Override
            public void onViewData(TreeVisEvent e) {
                addVisTab(e.getData().getDataKey(), e.getData().getModelKey(), e.getIndex());
            }
        });
        eventbus.addHandler(RFGenerateEvent.TYPE, new RFGenerateEventHandler() {
            @Override
            public void onStart(RFGenerateEvent e) {
                addConfusionMatrixTab(e.getData());
            }
        });
    }

	// Presenter methods

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

    @Override
    public void addDataTab(final String datakey) {
        clientFactory.getH2OService().getData(new InspectBuilder(datakey),
                new AsyncCallback<Inspect>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(Inspect result)
            {
                // FIXME: This most likely will not return all rows, we need to implement paging
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                for (InspectRow row : result.getRows()) {
                    Map<String, String> rowMap = new HashMap<String, String>();
                    data.add(rowMap);
                    for (ColumnDef column : result.getCols()) {
                        rowMap.put(column.getName(), row.getData(column.getName()).toString());
                    }
                }

                DataPanelPresenter presenter = new DataPanelPresenterImpl(clientFactory, datakey, data);
                clientFactory.getDoublePanelView().addDataTab(presenter.getView(), datakey);
            }
        });
    }

    @Override
    public void addVisTab(final String datakey, final String modelkey, final int tree) {
        clientFactory.getH2OService().getTreeView(
                new RFTreeViewBuilder(datakey, modelkey).setTreeNumber(tree),
                new AsyncCallback<RFTreeView>() {
            @Override
            public void onFailure(Throwable thrwbl) {
                logger.log(Level.SEVERE, thrwbl.toString());
                // FIXME: do a message box or something...
            }

            @Override
            public void onSuccess(RFTreeView treeview) {
                logger.log(Level.INFO, treeview.toString());
                // TODO: inject this here...
                clientFactory.getDoublePanelView().addVisTab(
                    new TreePanel(treeview, datakey, modelkey, tree),
                    datakey + "<br>" + modelkey + "<br>tree " + (tree + 1)
                );
            }
        });
    }

    @Override
    public void addConfusionMatrixTab(RF rf) {
        ConfusionMatrixPresenter presenter = new ConfusionMatrixPresenterImpl(clientFactory, rf);
        String title = "Confusion Matrix<br>" + rf.getDataKey() + "<br>" + rf.getModelKey();
        clientFactory.getDoublePanelView().addVisTab(presenter.getView(), title);
    }
}
