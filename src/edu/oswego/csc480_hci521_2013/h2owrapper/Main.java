package edu.oswego.csc480_hci521_2013.h2owrapper;

import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ImportUrl;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Parse;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreView;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ImportUrlBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.InspectBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ParseBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFViewBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.RFTreeViewBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.StoreViewBuilder;
import java.net.URL;

/**
 *
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
        parse(rest);

        // TODO: create something to monitor the progress
        Thread.sleep(3000);

        inspect(rest);
        rf(rest);

        // TODO: create something to monitor the progress
        Thread.sleep(3000);

        rfView(rest);
        rfViewTree(rest);
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

    private static void parse(RestHandler rest) throws Exception
    {
        URL url = new ParseBuilder("cars.csv").setHeader(true).setDestinationKey("cars.hex").build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        Parse val = rest.parse(json, Parse.class);
        System.out.println(val);
    }

    private static void inspect(RestHandler rest) throws Exception
    {
        URL url = new InspectBuilder("cars.hex").build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        // TODO: parsing
    }

    private static void rf(RestHandler rest) throws Exception
    {
        URL url = new RFBuilder("cars.hex").setModelKey("cars.model").build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        // TODO: parsing
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
        String json = rest.fetch(url);
        System.out.println(json);
        // TODO: parsing
    }

    private static void rfViewTree(RestHandler rest) throws Exception
    {
        URL url = new RFTreeViewBuilder("cars.hex", "cars.model").setTreeNumber(15).build();
        System.out.println(url);
        String json = rest.fetch(url);
        System.out.println(json);
        // TODO: parsing
    }
}
