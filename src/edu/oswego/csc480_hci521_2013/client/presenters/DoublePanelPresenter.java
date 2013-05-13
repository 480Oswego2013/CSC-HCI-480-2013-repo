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

import com.google.gwt.place.shared.Place;
import edu.oswego.csc480_hci521_2013.client.ui.TabLabelView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RF;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;

/**
 *
 */
public interface DoublePanelPresenter {

    void goTo(Place place);

    void addDataTab(String key);

    void addVisTab(String datakey, String modelkey, int tree);

    void addConfusionMatrixTab(RF randomForest, RFBuilder builder);

    void popout(TabLabelView tab);
    
    void close(TabLabelView tab);
}
