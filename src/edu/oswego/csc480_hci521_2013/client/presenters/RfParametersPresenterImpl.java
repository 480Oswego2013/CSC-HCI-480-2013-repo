package edu.oswego.csc480_hci521_2013.client.presenters;
import java.util.List;

import edu.oswego.csc480_hci521_2013.client.ClientFactory;

public class RfParametersPresenterImpl implements RfParametersPresenter {
    View view;

    public RfParametersPresenterImpl(ClientFactory factory){
        this.view = factory.getRfParametersPresenterView();

        view.setPresenter(this);
        view.buildUi();
    }

    //The headers are passed forward to the view.
    public void setHeaders(List<String> headers){
       view.setHeaders(headers);
    }

    @Override
    public View getView() {
        return view;
    }
}
