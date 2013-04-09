package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import edu.oswego.csc480_hci521_2013.server.ServerUrlEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author glebbyo
 */
public class ParseBuilderTest {
    
    UrlEncoder encoder;
    
    public ParseBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        encoder = new ServerUrlEncoder(); //is executed before each test.
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setDestinationKey method, of class ParseBuilder.
     * Compares the URL builded by the class after invoking setDestinationKey
     * method with the expected URL:
     * http://localhost:54321/Parse.json?destination_key=test.hex&source_key=test.csv
     * Uses "test.hex" as source key to construct the ParseBuilder object. 
     */
    @Test
    public void testSetDestinationKey() {
        System.out.println("setDestinationKey");
        String destinationKey = "test.hex";
        ParseBuilder instance = new ParseBuilder("test.csv");
        String expResult = "http://localhost:54321/Parse.json?destination_key=test.hex&source_key=test.csv";        
        String result = instance.setDestinationKey(destinationKey).build(encoder);;
        assertEquals(expResult,result);
    }

    /**
     * Test of setHeader method, of class ParseBuilder using false as a parameter.
     * Compares the URL builded by the class after invoking setHeader method 
     * with the expected URL: 
     * http://localhost:54321/Parse.json?source_key=test.hex&header=0
     * Uses "test.hex" as source key to construct the ParseBuilder object.
     */
    @Test
    public void testSetHeaderFalse() {
        System.out.println("setHeader");
        boolean header = false;
        ParseBuilder instance = new ParseBuilder("test.hex");
        String expResult = "http://localhost:54321/Parse.json?source_key=test.hex&header=0";
        String result = instance.setHeader(header).build(encoder);
        assertEquals(expResult, result);      
    }
    
    /**
     * Test of setHeader method, of class ParseBuilder using true as a parameter.
     * Compares the URL builded by the class after invoking setHeader method 
     * with the expected URL: 
     * http://localhost:54321/Parse.json?source_key=test.hex&header=1
     * Uses "test.hex" as source key to construct the ParseBuilder object.
     */
    @Test
    public void testSetHeaderTrue() {
        System.out.println("setHeader");
        boolean header = true;
        ParseBuilder instance = new ParseBuilder("test.hex");
        String expResult = "http://localhost:54321/Parse.json?source_key=test.hex&header=1";
        String result = instance.setHeader(header).build(encoder);
        assertEquals(expResult, result);      
    }
}
