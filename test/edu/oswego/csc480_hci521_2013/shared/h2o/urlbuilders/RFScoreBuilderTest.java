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
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kevin
 */
public class RFScoreBuilderTest {

    /**
     * url encoder to use in the tests.
     */
    private UrlEncoder encoder;

    /**
     * default constructor.
     */
    public RFScoreBuilderTest() {
    }

    /**
     * setup method called before tests.
     */
    @Before
    public final void setUp() {
        encoder = new ServerUrlEncoder();
    }

    /**
     * tear down method called after tests.
     */
    @After
    public final void tearDown() {
        encoder = null;
    }

    /**
     * Test of constructor with null data key.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorNullDataKey() {
        RFScoreBuilder instance = new RFScoreBuilder(null, "b");
    }

    /**
     * Test of constructor with null model key.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorNullModelKey() {
        RFScoreBuilder instance = new RFScoreBuilder("a", null);
    }

    /**
     * Test of constructor with null map.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorNullMap() {
        RFScoreBuilder instance = new RFScoreBuilder(null);
    }

    /**
     * Test of setResponseVariable method, of class RFScoreBuilder.
     */
    @Test
    public final void testSetResponseVariable() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("response_variable", "blerg");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        String value = "blerg";
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setResponseVariable(value);
        assertEquals(expResult.build(encoder), result.build(encoder));
    }

    /**
     * Test of testSetResponseVariable method with null argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetResponseVariableNull() {
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        instance.setResponseVariable(null);
    }

    /**
     * Test of setNtree method, of class RFScoreBuilder.
     */
    @Test
    public final void testSetNtree() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("ntree", "1");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        int value = 1;
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setNtree(value);
        assertEquals(expResult.build(encoder), result.build(encoder));
    }

    /**
     * Test of setNtree method, value of negative 1.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetNtreeNegative1() {
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        instance.setNtree(-1);
    }

    /**
     * Test of setClassWeights method, of class RFScoreBuilder.
     */
    @Test
    public final void testSetClassWeights() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("class_weights", "b=2.0,a=1.0");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        HashMap<String, Double> values = new HashMap<String, Double>();
        values.put("a", 1.0);
        values.put("b", 2.0);
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setClassWeights(values);

        assertEquals(expResult.build(encoder), result.build(encoder));
    }

    /**
     * Test of setClassWeights method, value of null.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetClassWeightsNull() {
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        instance.setClassWeights(null);
    }

    /**
     * Test of setNoConfusionMatrix method to false, of class RFScoreBuilder.
     */
    @Test
    public final void testSetNoConfusionMatrixFalse() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("no_confusion_matrix", "0");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        boolean value = false;
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setNoConfusionMatrix(value);
        assertEquals(expResult.build(encoder), result.build(encoder));
    }

    /**
     * Test of setNoConfusionMatrix method to true, of class RFScoreBuilder.
     */
    @Test
    public final void testSetNoConfusionMatrixTrue() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("no_confusion_matrix", "1");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        boolean value = true;
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setNoConfusionMatrix(value);
        assertEquals(expResult.build(encoder), result.build(encoder));
    }

    /**
     * Test of setClearConfusionMatrix method to false, of class RFScoreBuilder.
     */
    @Test
    public final void testSetClearConfusionMatrixFalse() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("clear_confusion_matrix", "0");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        boolean value = false;
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setClearConfusionMatrix(value);
        assertEquals(expResult.build(encoder), result.build(encoder));
    }

    /**
     * Test of setClearConfusionMatrix method to true, of class RFScoreBuilder.
     */
    @Test
    public final void testSetClearConfusionMatrixTrue() {
        HashMap<String, String> args = new HashMap<String, String>();
        args.put("data_key", "a");
        args.put("model_key", "b");
        args.put("clear_confusion_matrix", "1");
        RFScoreBuilder expResult = new RFScoreBuilder(args);

        boolean value = true;
        RFScoreBuilder instance = new RFScoreBuilder("a", "b");
        RFScoreBuilder result = instance.setClearConfusionMatrix(value);
        assertEquals(expResult.build(encoder), result.build(encoder));
    }
}