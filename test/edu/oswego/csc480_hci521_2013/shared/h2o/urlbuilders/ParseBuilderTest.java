/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
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
