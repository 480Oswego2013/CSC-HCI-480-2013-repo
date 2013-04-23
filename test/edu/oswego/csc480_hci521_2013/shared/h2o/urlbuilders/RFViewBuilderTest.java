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
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;

public class RFViewBuilderTest {
	RFViewBuilder instance;
	UrlEncoder encoder;

	@Before
	public void setUp() {
		encoder = new ServerUrlEncoder();
	}

	@BeforeClass
	public static void setUpClass() {
	}

	/**Tests the hashmap constructor for RFViewBuilder.
	 * 
	 */
	@Test
	public void testRFViewHashConstructor() {
		HashMap<String, String> args = new HashMap<String, String>();
		for (int x = -10; x <= 10; x++) {
			String intToString = String.valueOf	(x);
			args.put(intToString, intToString);
		}
		instance = new RFViewBuilder(args);
		encoder = new ServerUrlEncoder();
		String expResult = "http://localhost:54321/RFView.json?-10=-10" +
				"&3=3&2=2&10=10&1=1&0=0&7=7&-2=-2&6=6&-1=-1&5=5&-4=-4&" +
				"-3=-3&4=4&-6=-6&-5=-5&-8=-8&9=9&-7=-7&8=8&-9=-9";
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the model and data key constructor for RFViewBuilder
	 * 
	 */
	@Test
	public void testModelAndDataKey() {
		String dataKey = "dataKey";
		String modelKey = "modelKey";
		instance = new RFViewBuilder(dataKey, modelKey);
		encoder = new ServerUrlEncoder();
		String expResult = "http://localhost:54321/RFView.json?model_key=modelKey&data_key=dataKey";
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the random forest constructor for RFViewBuilder
	 * 
	 */
	@Test
	public void testRF() {
		String expResult = "http://localhost:54321/RFView.json?model_key=modelKey&data_key=dataKey&response_variable=0";
		String dataKey = "dataKey";
		String modelKey = "modelKey";
		int nTree = 1;
		int responseVariable = 0;
		boolean oobError = false;
		RF randomForest = new RF(dataKey, modelKey, nTree, responseVariable, oobError);
		instance = new RFViewBuilder(randomForest);
		encoder = new ServerUrlEncoder();
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the set response variable method
	 * 
	 */
	@Test
	public void testSetResponseVariable() {
		String expResult = "http://localhost:54321null?response_variable=1";
		Integer value = 1;
		instance = new RFViewBuilder();
		encoder = new ServerUrlEncoder();
		instance.setResponseVariable(1);
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the setNtree method
	 * 
	 */
	@Test
	public void testSetNTree() {
		String expResult = "http://localhost:54321null?ntree=1";
		Integer value = 1;
		instance = new RFViewBuilder();
		encoder = new ServerUrlEncoder();
		instance.setNtree(1);
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the set class weights method
	 * 
	 */
	@Test
	public void testSetClassWeights() {
		HashMap<String, Double> weights = new HashMap<String, Double>();
		for (int x = 0; x <= 10; x++) {
			weights.put(String.valueOf(x), (double) x);
		}
		String expResult = "http://localhost:54321null?class_weights=3%3D3.0%2C2%3D2.0"
				+ "%2C10%3D10.0%2C1%3D1.0%2C0%3D0.0%2C7%3D7.0%2C6%3D6.0%2C5%3D5.0%2C4%" + "3D4.0%2C9%3D9.0%2C8%3D8.0";
		instance = new RFViewBuilder();
		encoder = new ServerUrlEncoder();
		instance.setClassWeights(weights);
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the setNoConfustionMatrix method
	 * 
	 */
	@Test
	public void testSetNoConfusionMatrix() {
		String expResult = "http://localhost:54321null?no_confusion_matrix=1";
		instance = new RFViewBuilder();
		encoder = new ServerUrlEncoder();
		instance.setNoConfusionMatrix(true);
		assertEquals(instance.build(encoder), expResult);
		instance.setNoConfusionMatrix(false);
		expResult = "http://localhost:54321null?no_confusion_matrix=0";
		assertEquals(expResult, instance.build(encoder));
	}

	/**Tests the clearConfusionMatrixCache method
	 * 
	 */
	@Test
	public void testClearConfusionMatrixCache() {
		String expResult = "http://localhost:54321null?clear_confusion_matrix=1";
		instance = new RFViewBuilder();
		encoder = new ServerUrlEncoder();
		instance.clearConfusionMatrixCache(true);
		assertEquals(expResult, instance.build(encoder));
		expResult = "http://localhost:54321null?clear_confusion_matrix=0";
		instance.clearConfusionMatrixCache(false);
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
