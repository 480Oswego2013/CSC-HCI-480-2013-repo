package edu.oswego.csc480_hci521_2013.client.presenters;
import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

public class RfParametersPresenterImpl implements RfParametersPresenter {
    View view;

    public RfParametersPresenterImpl(ClientFactory factory){
        this.view = factory.getRfParametersPresenterView();

        view.setPresenter(this);
        view.buildUi();
    }

    @Override
    public View getView() {
        return view;
    }
}
