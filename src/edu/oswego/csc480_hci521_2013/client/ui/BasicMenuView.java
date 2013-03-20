package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.StoreView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.StoreViewBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicMenuView extends Composite implements MenuView {

	private Presenter presenter;
    static final Logger logger = Logger.getLogger(BasicMenuView.class.getName());
    private final H2OServiceAsync h2oService = GWT.create(H2OService.class);

	public BasicMenuView() {

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void buildGui() {
		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		MenuBar mainMenu = new MenuBar(false);
        mainMenu.addItem(getParsedDataMenu());

		mainPanel.add(mainMenu);
		initWidget(mainPanel);
	}

    private MenuItem getParsedDataMenu()
    {
        final MenuBar menu = new MenuBar(true);
        MenuItem item = new MenuItem("Parsed Data Sets", false, menu);
        h2oService.getDataStores(new StoreViewBuilder()
                .setFilter(".hex").setView(1024),
                new AsyncCallback<StoreView>() {

            @Override
            public void onFailure(Throwable caught) {
                // FIXME: we need some sort of general error handler
                logger.log(Level.SEVERE, caught.toString());
            }

            @Override
            public void onSuccess(StoreView result) {
                for (StoreView.Row row : result.getKeys()) {
                    // NOTE: there is no way to know from this if it is parsed or not,
                    //       but the naming convention is that parsed data ends in .hex
                    //       we could possibly do an inspect call on each piece to
                    //       check if it is parsed or not...
                    // TODO: we should check if there are more items still available.
                    if (row.getKey().endsWith(".hex")) {
                        menu.addItem(new MenuItem(
                                row.getKey(), false,
                                presenter.getMenuCommand(row.getKey())));
                    }
                }
            }
        });
        return item;
    }
}
