/* Author: Mike Hayes
 *
 */
package edu.oswego.csc480_hci521_2013.client.presenters;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import edu.oswego.csc480_hci521_2013.client.ClientFactory;
import edu.oswego.csc480_hci521_2013.client.events.RFParameterEvent;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

public class RfParametersPresenterImpl implements RfParametersPresenter {
    private View view;
    private String dataKey;
    private EventBus bus;

    public RfParametersPresenterImpl(ClientFactory factory, String dataKey){
        this.view = factory.getRfParametersPresenterView();
        this.dataKey = dataKey;
        this.bus = factory.getEventBus();

        view.setPresenter(this);
        view.buildUi();
    }

    public void fireRFParameterEvent(RFBuilder builder){
        bus.fireEvent(new RFParameterEvent(builder)); 
    }

    public String getDataKey(){
        return dataKey;
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
