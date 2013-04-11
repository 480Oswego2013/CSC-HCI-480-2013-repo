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

import static org.junit.Assert.assertEquals;

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

    /**Tests the ProgressBuilder constructor that takes a HashMap<String, String> as a parameter
     * 
     */
    @Test
    public void testProgressBuilderHashArgs1() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("test1", "test2");
        instance = new ProgressBuilder(args);
        encoder = new ServerUrlEncoder();
        String expResult = "http://localhost:54321/Progress.json?test1=test2";
        assertEquals(expResult, instance.build(encoder));
    }

    /**Tests the ProgressBuilder constructor that takes a HashMap<String, String>, with integer strings ranging from -10
     * to 10. 
     * 
     */
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
        assertEquals(expResult, instance.build(encoder));
    }
   
    /**Tests the ProgressBuilder constructor that takes a job and a destination key. 
     * Checks to see if the job and destination are encoded properly
     * 
     */
    @Test
    public void testProgressBuilderJobAndDestination() {
        String job = "1";
        String destinationKey = "2";
        instance = new ProgressBuilder(job, destinationKey);
        encoder = new ServerUrlEncoder();
        String expResult = "http://localhost:54321/Progress.json?destination_key=2&job=1";
        assertEquals(expResult, instance.build(encoder));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
        encoder = null;
    }
}
