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
// jamie made this
package edu.oswego.csc480_hci521_2013.client.presenters;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author parris
 * cant think of a test for this interface
 * no objects return a value or need values
 */
public class TabPanelPresenterTest {
    
    public TabPanelPresenterTest() {
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
     * Test of added method, of class TabPanelPresenter.
     */
    @Test
    public void testAdded() {
        System.out.println("added");
        TabPanelPresenter instance = new TabPanelPresenterImpl();
        instance.added();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of removed method, of class TabPanelPresenter.
     */
    @Test
    public void testRemoved() {
        System.out.println("removed");
        TabPanelPresenter instance = new TabPanelPresenterImpl();
        instance.removed();
        // TODO review the generated test code and remove the default call to fail.
    }

    public class TabPanelPresenterImpl implements TabPanelPresenter {

        public void added() {
        }

        public void removed() {
        }
    }
}
