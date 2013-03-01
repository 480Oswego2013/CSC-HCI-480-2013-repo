package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class BasicMenuView extends Composite implements MenuView {

	private Presenter presenter;

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
}
