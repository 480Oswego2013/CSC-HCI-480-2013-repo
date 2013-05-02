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

package edu.oswego.csc480_hci521_2013.shared.h2o;

import java.util.HashMap;
import java.util.List;

public interface RFRequest {
    String getDataKey();
    void setDataKey(String dataKey);

    String getResponseVariable();
    void setResponseVariable(String responseVariable);

    int getNumberTrees();
    void setNumberTrees(int nTrees);
    
    boolean getGini();
    void setGini(boolean gini);

    boolean getOutOfBagErrors();
    void setOutOfBagerrors(boolean oob);

    boolean getStratify();
    void setStratify(boolean stratify);
    
    int getNumberFeatures();
    void setNumberFeatures(int numberFeatures);
    
    List<String> getFeatures();
    void setFeatures(List<String> features);

    HashMap<String, Double> getClassWeights();
    void setClassWeights(HashMap<String, Double> values);
    
    String getClassVariable();
    void setClassVariable(String classVariable);
    
    boolean IsValid();
}
