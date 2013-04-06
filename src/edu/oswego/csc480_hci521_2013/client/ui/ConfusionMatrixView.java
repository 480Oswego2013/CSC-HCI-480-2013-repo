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
// Copyright 2013 State University of New York at Oswego
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import java.util.List;

public interface ConfusionMatrixView extends IsWidget {

    void hideProgress();

    void setProgress(String progressUpdate);

    void setClassificationError(String classificationError);

    void setResponseVariable(String responseVariable);

    void setNtree(String numberTrees);

    void setMtry(String mtry);

    void setRows(String rows);

    void setRowsSkipped(String rows);

    void setMatrixType(String matrixType);

    void setMatrixHeaders(List<String> headers);

    void setMatrixScores(List<List<Integer>> scores);

    void setErrors(List<String> errors);

    void setTotals(List<String> totals);

    void setTreesGenerated(String treesGenerated);

    void setLeavesMin(String leavesMin);

    void setLeavesMean(String leavesMean);

    void setLeavesMax(String leavesMax);

    void setDepthMin(String depthMin);

    void setDepthMean(String depthMean);

    void setDepthMax(String depthMax);

    Element getProgress();

    Element getClassificationError();

    Element getResponseVariable();

    Element getNtree();

    Element getMtry();

    Element getRowsSkipped();

    Element getRows();

    Element getMatrixType();

    FlexTable getMatrixTable();

    Element getTreesGenerated();

    Element getLeavesMin();

    Element getLeavesMean();

    Element getLeavesMax();

    Element getDepthMin();

    Element getDepthMean();

    Element getDepthMax();

    Style getStyle();

    interface Style extends CssResource {

        String tableHeader();

        String cfmatrixHighlight();
    }
}
