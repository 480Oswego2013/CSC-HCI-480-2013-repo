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
package edu.oswego.csc480_hci521_2013.client.presenters;
// jamie made this
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelView;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*; //used for isnot equal tests
public class RfParametersPresenterTest {
    
    public RfParametersPresenterTest() {
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
     * Test of getView method, of class RfParametersPresenter.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        RfParametersPresenter instance = new RfParametersPresenterImpl();
        RfParametersPresenter.View expResult = null;
        RfParametersPresenter.View result = instance.getView();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHeaders method, of class RfParametersPresenter.
     */
    @Test
    public void testSetHeaders() {
        
        // could not find the View class
        System.out.println("setHeaders");
        List<String> headers = null;
        RfParametersPresenter instance = new RfParametersPresenterImpl();
        instance.setHeaders(headers);
        
    }

    /**
     * Test of getDataKey method, of class RfParametersPresenter.
     */
    @Test
    public void testGetDataKey() {
        System.out.println("getDataKey");
        RfParametersPresenter instance = new RfParametersPresenterImpl();
        String expResult = "";
        String result = instance.getDataKey();
        assertEquals(expResult, result);
    }
     @Test
    public void testGetDataKeyFail() {
        System.out.println("diffrentDatakey");
        RfParametersPresenter instance = new RfParametersPresenterImpl();
        String expResult = "Apples";
        String result = instance.getDataKey();
        assertThat(expResult,not(result));// checks that the results are not equal
        
        
    }
    

    /**
     * Test of fireRFParameterEvent method, of class RfParametersPresenter.
     */
    @Test
    public void testFireRFParameterEvent() {
        System.out.println("fireRFParameterEvent");
        RFBuilder builder = null;
        RfParametersPresenter instance = new RfParametersPresenterImpl();
        instance.fireRFParameterEvent(builder);
    }

    public class RfParametersPresenterImpl implements RfParametersPresenter {

        public View getView() {
            return null;
        }

        public void setHeaders(List<String> headers) {
        }

        public String getDataKey() {
            return "";
        }

        public void fireRFParameterEvent(RFBuilder builder) {
        }

		@Override
		public H2OServiceAsync getH2OService() {
			return null;
		}
    }
}
