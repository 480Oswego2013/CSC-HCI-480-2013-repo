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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author slave
 */
public class InspectBuilderTest {
    
    public InspectBuilderTest() {
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
     * Test of newInstance constructor, of class InspectBuilder.
     */
    @Test
    public void newInstance()
    {
        System.out.println("newInstance");        
        InspectBuilder instance = new InspectBuilder();        
        assertNotNull(instance);
        assertTrue(instance instanceof InspectBuilder);
    }
        
    /**
     * Test of newInstanceParam constructor, of class InspectBuilder.
     */
    @Test
    public void newInstanceParam()
    {        
        try {
            System.out.println("newInstanceParam");            
            String key = "key";
            String page = "Inspect.json";
            
            InspectBuilder instance = new InspectBuilder(key);
            assertNotNull(instance);
            assertTrue(instance instanceof InspectBuilder);
                       
            // Test if AbstractBuilder "page" field is set.
            Field pageField = InspectBuilder.class.getSuperclass().getDeclaredField("page");
            pageField.setAccessible(true);
            String pageValue = (String)pageField.get(instance);
            assertEquals("/"+page, pageValue);
            
            // Test if argument is added to inherited HashMap<String, ArrayList<String>> args.
            Field argsField = InspectBuilder.class.getSuperclass().getDeclaredField("args");
            argsField.setAccessible(true);
            HashMap<String, String> arg=(HashMap<String, String>)argsField.get(instance);
            boolean containsKey = arg.containsKey(key);
            assertTrue(containsKey);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of setView method, of class InspectBuilder.
     */
    @Test
    public void testSetView() {
        try {
            System.out.println("setView");
            Integer view = 101;

            InspectBuilder instance = new InspectBuilder();
            InspectBuilder expResult = instance.setView(view);

            assertNotNull(expResult);
            assertTrue(expResult instanceof InspectBuilder);
            
            // Test if "view" is added to inherited HashMap<String, ArrayList<String>> args.
            Field argsField = InspectBuilder.class.getSuperclass().getDeclaredField("args");
            argsField.setAccessible(true);
            HashMap<String, String> args = (HashMap<String, String>) argsField.get(instance);
                        
            boolean containsKey = args.containsKey("view");
            assertTrue(containsKey);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    /**
     * Test of setOffset method, of class InspectBuilder.
     */
    @Test
    public void testSetOffset() {
        try {
            System.out.println("setOffset");
            Long offset = new Long(101);
            
            InspectBuilder instance = new InspectBuilder();
            InspectBuilder expResult = instance.setOffset(offset);
            
            assertNotNull(expResult);
            assertTrue(expResult instanceof InspectBuilder);
                        
            // Test if "Offset" is added to inherited HashMap<String, ArrayList<String>> args.
            Field argsField = InspectBuilder.class.getSuperclass().getDeclaredField("args");
            argsField.setAccessible(true);
            Map<String, String> args = (Map<String, String>) argsField.get(instance);
          
            boolean containsKey = args.containsKey("offset");
            assertTrue(containsKey);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(InspectBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
