package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setView method, of class StoreViewBuilder.
     */
    @Test
    public void testSetView() {
        System.out.println("setView");
        Integer view = 25;
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?view=25";
        StoreViewBuilder result = instance.setView(view);
        assertEquals(expResult, result.build());
    }

    /**
     * Test of setOffset method, of class StoreViewBuilder.
     */
    @Test
    public void testSetOffset() {
        System.out.println("setOffset");
        Integer offset = 51;
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?offset=51";
        StoreViewBuilder result = instance.setOffset(offset);
        assertEquals(expResult, result.build());
    }

    /**
     * Test of setFilter method, of class StoreViewBuilder.
     */
    @Test
    public void testSetFilter() {
        System.out.println("setFilter");
        String value = "blerg";
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?filter=blerg";
        StoreViewBuilder result = instance.setFilter(value);
        assertEquals(expResult, result.build());
    }
}