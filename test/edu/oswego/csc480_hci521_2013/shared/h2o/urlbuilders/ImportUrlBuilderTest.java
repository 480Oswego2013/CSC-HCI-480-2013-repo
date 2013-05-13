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

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImportUrlBuilderTest {

	//make the jar to run my test
	ImportUrlBuilder myJar; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//lets me use my jar without initializing in every test
		myJar = new ImportUrlBuilder();
	}

	@After
	public void tearDown() throws Exception {
	}

	/* a test to see if the set key method is working*/
	@Test
	public void testIfNull() {
		String myString = "THUNDER CATS!!!!";
		myJar.setKey(myString);
		assertNotNull("key is not Null",myJar);		
	}
	
	/* check that the values are the same */
	@Test 
	public void checkKeyValue() throws Exception {
		String myString = "THUNDER CATS!!!!";
	ImportUrlBuilder keyValue = myJar.setKey(myString);
	ImportUrlBuilder otherValue = myJar.setKey(myString);
	
	// return type is of import builder
	assertEquals("the key value is equal",otherValue,keyValue);	
	}
        /*
         * this test is checking that the value is null
         */
        @Test 
	public void checkKeyValueNull() throws Exception {
	String myString = "";
	ImportUrlBuilder keyValue = myJar.setKey(myString);
	
	// return type is of import builder
	assertNull("the key value is Null",keyValue);	
	}
  
                /*
         * this test is checking that the value is null
         */
        @Test 
	public void checkKeyValueNotNull() throws Exception {
	String myString = "apples";
	ImportUrlBuilder keyValue = myJar.setKey(myString);
	
	// return type is of import builder
	assertNotNull("the key value is Null",keyValue);	
	}
 

}