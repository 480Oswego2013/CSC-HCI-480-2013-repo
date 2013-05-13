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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class StoreViewBuilderTest {

    UrlEncoder encoder;

    /**
     * setup test resources.
     */
    @Before
    public final void setUp() {
        encoder = new ServerUrlEncoder();
    }

    /**
     * tear down test resources.
     */
    @After
    public final void tearDown() {
        encoder = null;
    }

    /**
     * Test of setView method, of class StoreViewBuilder.
     */
    @Test
    public final void testSetView() {
        int view = 1;
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?view=1";
        StoreViewBuilder result = instance.setView(view);
        assertEquals(expResult, result.build(encoder));
    }

    /**
     * Test of setView method with negative argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetViewNegative() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setView(-1);
    }

    /**
     * Test of setView method with Integer.MAX_VALUE.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetViewMaxInt() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setView(Integer.MAX_VALUE);
    }

    /**
     * Test of setView method with 1025.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetView1025() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setView(1025);
    }

    /**
     * Test of setOffset method, of class StoreViewBuilder.
     */
    @Test
    public final void testSetOffset() {
        Integer offset = 1;
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?offset=1";
        StoreViewBuilder result = instance.setOffset(offset);
        assertEquals(expResult, result.build(encoder));
    }

    /**
     * Test of setOffset method with negative arg.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetOffsetNegative() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setOffset(-1);
    }

    /**
     * Test of setOffset method with max int arg.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetOffsetMaxInt() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setOffset(Integer.MAX_VALUE);
    }

    /**
     * Test of setOffset method with arg of 1025.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetOffset1025() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setOffset(1025);
    }

    /**
     * Test of setFilter method, of class StoreViewBuilder.
     */
    @Test
    public final void testSetFilter() {
        String value = "blerg";
        StoreViewBuilder instance = new StoreViewBuilder();
        String expResult = "http://localhost:54321/StoreView.json?filter=blerg";
        StoreViewBuilder result = instance.setFilter(value);
        assertEquals(expResult, result.build(encoder));
    }

    /**
     * Test of setFilter method with null argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetFilterNull() {
        StoreViewBuilder instance = new StoreViewBuilder();
        instance.setFilter(null);
    }
}
