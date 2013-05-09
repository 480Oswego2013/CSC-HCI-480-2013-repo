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
package edu.oswego.csc480_hci521_2013.client.ui;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gwt.place.shared.Place;

import edu.oswego.csc480_hci521_2013.client.place.DoublePanelPlace;
import edu.oswego.csc480_hci521_2013.client.presenters.DoublePanelPresenter;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class DoublePanelPresenterTest {
    
    @Mock
    private RFBuilder modelParameters;
    
    public DoublePanelPresenterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(modelParameters.getResponseVariable()).thenReturn("cylinders");
        when(modelParameters.getNtree()).thenReturn(1000);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of goTo method, of class DoublePanelPresenter.
     */
    @Test
    public void testGoTo() {
    	try {
        Place place = new DoublePanelPlace();
        DoublePanelPresenter instance = new DoublePanelPresenterImpl();
        instance.goTo(place);
    	} catch (Exception e) {
    		assert(false);
    	}
    }

    /**
     * Test of addDataTab method, of class DoublePanelPresenter.
     */
    @Test
    public void testAddDataTab() {
    	try {
        String key = "24";
        DoublePanelPresenter instance = new DoublePanelPresenterImpl();
        instance.addDataTab(key);
    	} catch (Exception e) {
    		assert(false);
    	}
    }

    /**
     * Test of addVisTab method, of class DoublePanelPresenter.
     */
    @Test
    public void testAddVisTab() {
    	try {
        String datakey = "23";
        String modelkey = "34";
        int tree = 0;
        DoublePanelPresenter instance = new DoublePanelPresenterImpl();
        instance.addVisTab(datakey, modelkey, tree);
    	} catch (Exception e) {
    		assert(false);
    	}
    }

    /**
     * Test of addConfusionMatrixTab method, of class DoublePanelPresenter.
     */
    @Test
    public void testAddConfusionMatrixTabNullForest() {
    	try {
        RF randomForest = null;
        RFBuilder build = null;
        DoublePanelPresenter instance = new DoublePanelPresenterImpl();
        instance.addConfusionMatrixTab(randomForest, modelParameters);
        instance.addConfusionMatrixTab(randomForest,build);
    	} catch (Exception e) {
    		assert(false);
    	}
    }
    
    /**
     * Test of popout method, of class DoublePanelPresenter.
     */
    @Test
    public void testPopoutNullTab() {
    	try {
        TabLabelView tab = null;
        DoublePanelPresenter instance = new DoublePanelPresenterImpl();
        instance.popout(tab);
    	} catch (Exception e) {
    		assert(false);
    	}
    }

    /**
     * Test of close method, of class DoublePanelPresenter.
     */
    @Test
    public void testClose() {
    	try {
        TabLabelView tab = null;
        DoublePanelPresenter instance = new DoublePanelPresenterImpl();
        instance.close(tab);
    	} catch (Exception e) {
    		assert(false);
    	}
    }

    public class DoublePanelPresenterImpl implements DoublePanelPresenter {

        public void goTo(Place place) {
        }

        public void addDataTab(String key) {
        }

        public void addVisTab(String datakey, String modelkey, int tree) {
        }

        public void addConfusionMatrixTab(RF randomForest, RFBuilder builder) {
        }

        public void popout(TabLabelView tab) {
        }

        public void close(TabLabelView tab) {
        }
    }
}