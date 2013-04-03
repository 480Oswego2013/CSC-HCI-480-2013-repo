package edu.oswego.csc480_hci521_2013.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import edu.oswego.csc480_hci521_2013.client.place.ConfusionMatrixPlace;
import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.place.DataTablePlace;
import edu.oswego.csc480_hci521_2013.client.place.TreeVisPlace;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;

public class AppActivityMapper implements ActivityMapper {

    private final DoublePanelView doubleView;
    private final PlaceController controller;
    private final H2OServiceAsync h2oService;

    // TODO: this should probably take an activity factory of some sort
    //       so that we do not eventually have a huge number of constructor
    //       args with all the default views...
    public AppActivityMapper(DoublePanelView doubleView, PlaceController controller, H2OServiceAsync h2oService) {
        this.doubleView = doubleView;
        this.controller = controller;
        this.h2oService = h2oService;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof DoublePanelPlace) {
            return new DoublePanelActivity(
                    (DoublePanelPlace) place,
                    doubleView,
                    controller,
                    h2oService);
        } else if (place instanceof DataTablePlace) {
            return new DataPanelActivity(
                    (DataTablePlace)place, h2oService);
        } else if (place instanceof TreeVisPlace) {
            return new TreeVisActivity((TreeVisPlace)place, h2oService);
        } else if (place instanceof ConfusionMatrixPlace) {
            return new ConfusionMatrixActivity(
                    (ConfusionMatrixPlace) place, h2oService);
        }
        return null;
    }
}
