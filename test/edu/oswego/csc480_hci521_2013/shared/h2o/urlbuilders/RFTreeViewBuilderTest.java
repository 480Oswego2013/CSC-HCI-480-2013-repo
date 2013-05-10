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
 * @author Ethan
 */
public class RFTreeViewBuilderTest {
    RFTreeViewBuilder instance;
	UrlEncoder encoder;
    public RFTreeViewBuilderTest() {
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
     * Test of setTreeNumber method, of class RFTreeViewBuilder.
     */
    @Test
    public void testSetTreeNumber() {
        System.out.println("setTreeNumber");
        int value = 1;
        instance = new RFTreeViewBuilder();
        encoder = new ServerUrlEncoder();
        String expResult = "http://localhost:54321null?tree_number=1";
        String result = instance.setTreeNumber(value).build(encoder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    /**
     * Test of the data key and the model key of class RFTreeViewBuilder.
     */
    @Test
    public void testDataandModelKey() {
        String data_key = "data_key";
        String model_key = "model_key";
        instance = new RFTreeViewBuilder(data_key, model_key);
        encoder = new ServerUrlEncoder();
        String expResult = "http://localhost:54321/RFTreeView.json?model_key=model_key&data_key=data_key";
        assertEquals(expResult, instance.build(encoder));
    }
    /**
     * Test of setResponseVariable method, of class RFTreeViewBuilder.
     */
 	@Test
	public void testSetResponseVariable() {
		String expResult = "http://localhost:54321null?response_variable=1";
		Integer value = 1;
		instance = new RFTreeViewBuilder();
		encoder = new ServerUrlEncoder();
		instance.setResponseVariable(1);
		assertEquals(expResult, instance.build(encoder));
	}
}