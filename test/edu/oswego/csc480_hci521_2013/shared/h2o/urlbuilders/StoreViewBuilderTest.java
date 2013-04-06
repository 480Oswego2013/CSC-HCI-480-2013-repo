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