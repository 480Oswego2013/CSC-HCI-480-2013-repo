package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import edu.oswego.csc480_hci521_2013.server.ServerUrlEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class StoreViewBuilderTest {

    UrlEncoder encoder;

    public StoreViewBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        encoder = new ServerUrlEncoder();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setView method, of class StoreViewBuilder.
     */
    @Test
    public void testSetView() {
        Integer view = 25;
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?view=25";
        StoreViewBuilder result = instance.setView(view);
        assertEquals(expResult, result.build(encoder));
    }

    /**
     * Test of setOffset method, of class StoreViewBuilder.
     */
    @Test
    public void testSetOffset() {
        Integer offset = 51;
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?offset=51";
        StoreViewBuilder result = instance.setOffset(offset);
        assertEquals(expResult, result.build(encoder));
    }

    /**
     * Test of setFilter method, of class StoreViewBuilder.
     */
    @Test
    public void testSetFilter() {
        String value = "blerg";
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?filter=blerg";
        StoreViewBuilder result = instance.setFilter(value);
        assertEquals(expResult, result.build(encoder));
    }
}