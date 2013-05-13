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

/**
 *
 * @author Ethan Neil
 */
package edu.oswego.csc480_hci521_2013.client.presenters;

import edu.oswego.csc480_hci521_2013.client.ui.TreePanelView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFTreeView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author Ethan
 */
public class TreePanelPresenterImplTest {
    @Mock
    RFTreeView rft;
    @Mock
    TreePanelView treepv;
    
    
    public TreePanelPresenterImplTest() {
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
     * Test of getData method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testGetData() {
        TreePanelView tpv = null;
        RFTreeView data = rft;
        String data_key = null;
        String model_key = null;
        int treeIndex = 0;
        System.out.println("getData");
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        RFTreeView expResult = rft;
        RFTreeView result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getDatakey method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testGetDatakey() {
        System.out.println("getDatakey");
         TreePanelView tpv = null;
        RFTreeView data = null;
        String data_key = "data_key";
        String model_key = null;
        int treeIndex = 0;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        String expResult = "data_key";
        String result = instance.getDatakey();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getModelkey method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testGetModelkey() {
        System.out.println("getModelkey");
        TreePanelView tpv = null;
        RFTreeView data = null;
        String data_key = null;
        String model_key = "model_key";
        int treeIndex = 0;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        String expResult = "model_key";
        String result = instance.getModelkey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTreeIndex method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testGetTreeIndex() {
        System.out.println("getTreeIndex");
        TreePanelView tpv = null;
        RFTreeView data = null;
        String data_key = null;
        String model_key = null;
        int treeIndex = 1;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        int expResult = 1;
        int result = instance.getTreeIndex();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of added method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testAdded() {
        System.out.println("added");
        TreePanelView tpv = null;
        RFTreeView data = null;
        String data_key = null;
        String model_key = null;
        int treeIndex = 0;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        instance.added();
        
    }

    /**
     * Test of removed method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testRemoved() {
        System.out.println("removed");
        TreePanelView tpv = null;
        RFTreeView data = null;
        String data_key = null;
        String model_key = null;
        int treeIndex = 0;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        instance.removed();
      
    }

    /**
     * Test of getView method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        TreePanelView tpv = treepv;
        RFTreeView data = null;
        String data_key = null;
        String model_key = null;
        int treeIndex = 0;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        TreePanelView expResult = treepv;
        TreePanelView result = instance.getView();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setView method, of class TreePanelPresenterImpl.
     */
    @Test
    public void testSetView() {
        System.out.println("setView");
        TreePanelView view = treepv;
        TreePanelView tpv = null;
        RFTreeView data = null;
        String data_key = null;
        String model_key = null;
        int treeIndex = 0;
        TreePanelPresenterImpl instance = new TreePanelPresenterImpl(tpv, data, data_key, model_key, treeIndex);
        instance.setView(view);
       
    }
}