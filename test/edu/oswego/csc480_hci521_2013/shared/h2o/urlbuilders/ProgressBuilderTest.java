package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.oswego.csc480_hci521_2013.server.ServerUrlEncoder;

public class ProgressBuilderTest {
	ProgressBuilder instance;
    UrlEncoder encoder;

    @Before
    public void setUp() {
        encoder = new ServerUrlEncoder();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Test
    public void testProgressBuilderHashArgs1() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("test1", "test2");
        instance = new ProgressBuilder(args);
        encoder = new ServerUrlEncoder();
        String expResult = "http://localhost:54321?";
        assert (expResult.equalsIgnoreCase("http://localhost:54321/Progress.json?test1=test2"));
    }

    @Test
    public void testProgressBuilderHashArgs2() {
        HashMap<String, String> args = new HashMap<String, String>();
        for (int x = -10; x <= 10; x++) {
            String intToString = String.valueOf(x);
            args.put(intToString, intToString);
        }
        instance = new ProgressBuilder(args);
        encoder = new ServerUrlEncoder();
        String expResult = "http://localhost:54321/Progress.json?-10=-10&3=3&2=2&10=10&1=1&0=0&7=7&-2=-2&6=6&-1"
                + "=-1&5=5&-4=-4&-3=-3&4=4&-6=-6&-5=-5&-8=-8&9=9&-7=-7&8=8&-9=-9";
        assert (expResult.equalsIgnoreCase(instance.build(encoder)));
    }
   
    @Test
    public void testProgressBuilderJobAndDestination() {
        String job = "1";
        String destinationKey = "2";
        instance = new ProgressBuilder(job, destinationKey);
        encoder = new ServerUrlEncoder();
        String expResult = "destination_key=2&job=1";
        assert (expResult.equalsIgnoreCase(instance.build(encoder)));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
        encoder = null;

    }
}
