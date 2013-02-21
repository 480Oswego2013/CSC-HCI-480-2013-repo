package edu.oswego.csc480_hci521_2013.h2owrapper;

import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.ImportUrl;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.Parse;
import edu.oswego.csc480_hci521_2013.h2owrapper.json.objects.StoreView;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ImportUrlBuilder;
import edu.oswego.csc480_hci521_2013.h2owrapper.urlbuilders.ParseBuilder;
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
        inspect(rest);
        rf(rest);
        rfView(rest);
        rfViewTree(rest);
        storeView(rest);
    }

    private static void importUrl(RestHandler rest) throws Exception
    {
        URL url = new ImportUrlBuilder("https://raw.github.com/0xdata/h2o/master/smalldata/cars.csv").setKey("cars").build();
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
    }

    private static void rf(RestHandler rest) throws Exception
    {
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
    }

    private static void rfViewTree(RestHandler rest) throws Exception
    {
    }
}
