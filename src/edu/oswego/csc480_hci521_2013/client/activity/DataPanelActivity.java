package edu.oswego.csc480_hci521_2013.client.activity;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEvent;
import edu.oswego.csc480_hci521_2013.client.events.InspectDataEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFGenerateEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEvent;
import edu.oswego.csc480_hci521_2013.client.events.RFProgressEventHandler;
import edu.oswego.csc480_hci521_2013.client.events.TreeVisEvent;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace.Location;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace.PanelType;
import edu.oswego.csc480_hci521_2013.client.place.PopoutPanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

public class DataPanelActivity extends AbstractPanelActivity implements
		DataPanelPresenter {

	static final Logger logger = Logger.getLogger(DataPanelActivity.class
			.getName());
	private ClientFactory clientFactory;
	private RF randomForest;

	EventBus eventBus;
	View view;
	H2OServiceAsync h2oService;
	
	String dataKey;
	
	boolean isPopout = false;

	public DataPanelActivity(Location loc, DoublePanelPlace place,
			ClientFactory clientFactory) {
		super(loc);
		this.clientFactory = clientFactory;

		eventBus = clientFactory.getEventBus();
		h2oService = clientFactory.getH2OService();
		dataKey = place.getDataKey();
	}
	
	public DataPanelActivity(PopoutPanelPlace place, ClientFactory clientFactory) {
        	this.clientFactory = clientFactory;
        
        	eventBus = clientFactory.getEventBus();
        	h2oService = clientFactory.getH2OService();
        	dataKey = place.getDataKey();
        	isPopout = true;
        }

	// Activity lifecycle methods

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getDataPanelView();
		view.setPresenter(this);
		panel.setWidget(view);
		bind();
		
		if(isPopout()) addDataTab(dataKey);
	}
	
	@Override
	public boolean isPopout() {
		return isPopout;
	}
	
	@Override
	public void popPanel(IsWidget panel) {
		view.removeDataTab(view.getActivePanel());
	}
	
	@Override
	public void addPanel(IsWidget panel, String title) {
		view.addDataTab(title, (TabView)panel);
	}

	// Presenter methods

	@Override
	public Command getGenerateCommand() {
		return new Command() {
			@Override
			public void execute() {
				logger.log(Level.INFO, "Generating Forest");
				h2oService.generateRandomForest(new RFBuilder(dataKey),
						new AsyncCallback<RF>() {
							@Override
							public void onFailure(Throwable thrwbl) {
								logger.log(Level.SEVERE, thrwbl.toString());
								// FIXME: do a message box or something...
							}

							@Override
							public void onSuccess(RF rf) {
								logger.log(Level.INFO, "Forest Started");
								randomForest = rf;

								eventBus.fireEvent(new RFGenerateEvent(rf));

								view.getActivePanel().forestStarted();
								startRFViewPoller();
							}
						});
			}
		};
	}

	@Override
	public Command getTreeVisCommand(final int index) {
		return new Command() {
			@Override
			public void execute() {
				eventBus.fireEvent(new TreeVisEvent(randomForest, index));
			}
		};
	}
	
	@Override
	public Command getPopOutCommand() {
		return new Command() {
			@Override
			public void execute() {
				PopoutPanelPlace place = new PopoutPanelPlace();
				place.setType(PanelType.DATA);
				place.setDataKey(dataKey);
				popout(place, view.getActivePanel(), view.getTabTitle(view.getActivePanel()));
			}
		};
	}
	
	@Override
	public Command getCloseCommand() {
		return new Command() {
			@Override
			public void execute() {
				view.removeDataTab(view.getActivePanel());
			}
		};
	}

	// Utility methods

	private void bind() {
		EventBus eventbus = clientFactory.getEventBus();
		eventbus.addHandler(InspectDataEvent.TYPE,
				new InspectDataEventHandler() {
					@Override
					public void onViewData(InspectDataEvent e) {
						addDataTab(e.getName());
					}
				});
		eventbus.addHandler(RFGenerateEvent.TYPE, new RFGenerateEventHandler() {
			@Override
			public void onStart(RFGenerateEvent e) {
				// addConfusionMatrixTab(e.getData());
			}
		});
		eventbus.addHandler(RFProgressEvent.TYPE, new RFProgressEventHandler() {
			@Override
			public void onDataUpdate(RFProgressEvent e) {
				if (isOurData(e.getData())) {
					RFView rfview = e.getData();
					logger.log(Level.INFO, rfview.toString());
					ResponseStatus status = rfview.getResponse();
					if (status.isPoll()) {
						int done = status.getProgress();
						int total = status.getProgressTotal();
						logger.log(Level.INFO, "Trees: Generated " + done
								+ " of " + total);
						view.getActivePanel().setForestStatus(done, total);
					} else {
						logger.log(Level.INFO, "Forest finished");
						view.getActivePanel().forestFinish(rfview.getNtree());
					}
				}
			}
		});
	}

	private boolean isOurData(RFView data) {
		if (data.getDataKey().equals(randomForest.getDataKey())
				&& data.getModelKey().equals(randomForest.getModelKey())) {
			return true;
		}
		return false;
	}

	public void addDataTab(final String dataKey) {
		this.dataKey = dataKey;
		clientFactory.getH2OService().getParsedData(dataKey,
				new AsyncCallback<List<Map<String, String>>>() {
					@Override
					public void onFailure(Throwable caught) {
						logger.log(Level.SEVERE, caught.toString());
						// FIXME: do a message box or something...
					}

					@Override
					public void onSuccess(List<Map<String, String>> result) {
						view.addDataTab(dataKey, result);
					}
				});
	}

	private void startRFViewPoller() {
		// TODO: this should probably be somewhere else...
		Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
			boolean isFinished = false;

			@Override
			public boolean execute() {
				if (isFinished) {
					logger.log(Level.INFO,
							"Polling forest generation has finished");
					return false;
				}

				logger.log(Level.INFO, "Polling forest generation progress");
				h2oService.getRandomForestView(randomForest.getDataKey(),
						randomForest.getModelKey(),
						new AsyncCallback<RFView>() {
							@Override
							public void onFailure(Throwable thrwbl) {
								logger.log(Level.SEVERE, thrwbl.toString());
							}

							@Override
							public void onSuccess(RFView rfview) {
								if (!rfview.getResponse().isPoll()) {
									isFinished = true;
								}
								eventBus.fireEvent(new RFProgressEvent(rfview));
							}
						});
				return true;
			}
		}, 1000);
	}

}
