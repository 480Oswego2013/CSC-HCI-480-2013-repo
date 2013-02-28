package edu.oswego.csc480_hci521_2013.h2owrapper;

import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ImportUrl;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Inspect;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Parse;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Progress;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.RF;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.RFTreeView;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.RFView;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ResponseStatus;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreView;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ImportUrlBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ParseBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ProgressBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFViewBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFTreeViewBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.StoreViewBuilder;
import java.net.URL;

/**
 * Examples of usage
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        RestHandler rest = new RestHandler();

        importUrl(rest);
        ResponseStatus status = parse(rest);
        progress(rest, status);
        inspect(rest);
        rf(rest);
        rfView(rest);
        rfTreeView(rest);
        storeView(rest);
    }

    private static void importUrl(RestHandler rest) throws Exception
    {
        URL url = new ImportUrlBuilder("https://raw.github.com/0xdata/h2o/master/smalldata/cars.csv").setKey("cars.csv").build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        ImportUrl val = rest.parse(json, ImportUrl.class);
        System.out.println(val);
    }

    private static ResponseStatus parse(RestHandler rest) throws Exception
    {
        URL url = new ParseBuilder("cars.csv").setHeader(true).setDestinationKey("cars.hex").build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        Parse val = rest.parse(json, Parse.class);
        System.out.println(val);
        return val.getResponse();
    }

    private static void inspect(RestHandler rest) throws Exception
    {
        URL url = new InspectBuilder("cars.hex").build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        Inspect val = rest.parse(json, Inspect.class);
        System.out.println(val);
    }

    private static void rf(RestHandler rest) throws Exception
    {
        URL url = new RFBuilder("cars.hex").setModelKey("cars.model").setOutOfBagErrorEstimate(false).build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        RF val = rest.parse(json, RF.class);
        System.out.println(val);
    }

    private static void storeView(RestHandler rest) throws Exception
    {
        URL url = new StoreViewBuilder().build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        StoreView val = rest.parse(json, StoreView.class);
        System.out.println(val);
    }

    private static void rfView(RestHandler rest) throws Exception
    {
        URL url = new RFViewBuilder("cars.hex", "cars.model").build();
        System.out.println(url);

        for (;;) {
            String json = rest.fetch(url);
            System.out.println(json);
            RFView val = rest.parse(json, RFView.class);
            System.out.println(val);
            if (val.getResponse().isPoll()) {
                System.out.println("Progress: " + val.getResponse().getProgress());
                Thread.sleep(500);
            }
            else {
                break;
            }
        }
    }

    private static void rfTreeView(RestHandler rest) throws Exception
    {
        URL url = new RFTreeViewBuilder("cars.hex", "cars.model").setTreeNumber(15).build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        RFTreeView val = rest.parse(json, RFTreeView.class);
        System.out.println(val);
    }

    private static void progress(RestHandler rest, ResponseStatus status) throws Exception
    {
        if (!status.isRedirect() || !status.getRedirectRequest().equals("Progress")) {
            return;
        }
        String job = status.getRedirectRequestArgs().get("job");
        String key = status.getRedirectRequestArgs().get("destination_key");

        URL url = new ProgressBuilder(job, key).build();
        System.out.println(url);
        Progress val;
        for (;;) {
            String json = rest.fetch(url);
            System.out.println(json);
            val = rest.parse(json, Progress.class);
            System.out.println(val);
            if (val.getResponse().isPoll()) {
                System.out.println("Progress: " + val.getResponse().getProgress());
                Thread.sleep(500);
            }
            else {
                break;
            }
        }
    }
}
