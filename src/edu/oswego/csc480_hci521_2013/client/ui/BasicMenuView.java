package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import edu.oswego.csc480_hci521_2013.client.services.H2OService;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
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
		for(int i=1; i<6; i++) {
			MenuBar menu = new MenuBar(true);
			MenuItem item = new MenuItem("Item "+i, false, menu);
			for(int j=1; j<11; j++) {
				if(j == 10) {
					MenuBar innerMenu = new MenuBar(true);
					for(int k=1; k<11; k++)
						innerMenu.addItem(new MenuItem("Inner Inner Item "+k, false, presenter.getMenuCommand()));
					menu.addItem("Inner Item "+j, false, innerMenu);
				} else
					menu.addItem(new MenuItem("Inner Item "+j, false, presenter.getMenuCommand()));
			}
			mainMenu.addItem(item);
		}

		mainPanel.add(mainMenu);
		initWidget(mainPanel);
	}

    private MenuItem getParsedDataMenu()
    {
        final MenuBar menu = new MenuBar(true);
        MenuItem item = new MenuItem("Parsed Data Sets", false, menu);
        h2oService.getParsedDataKeys(new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught)
            {
                logger.log(Level.SEVERE, caught.toString());
            }

            @Override
            public void onSuccess(List<String> result)
            {
                for (String key: result) {
                    menu.addItem(new MenuItem(key, false, presenter.getMenuCommand()));
                }
            }
        });
        return item;
    }
}
