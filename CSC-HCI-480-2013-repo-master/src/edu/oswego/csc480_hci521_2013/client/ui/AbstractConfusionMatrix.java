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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import java.util.List;

/**'
 *
 */
public abstract class AbstractConfusionMatrix extends Composite implements ConfusionMatrixView {

    @Override
    public void hideProgress() {
        getProgress().getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.HIDDEN);
    }

    @Override
    public void setProgress(String progressUpdate) {
        this.getProgress().setAttribute("value", progressUpdate);
    }

    @Override
    public void setClassificationError(String classificationError) {
        this.getClassificationError().setInnerText(classificationError);
    }

    @Override
    public void setResponseVariable(String responseVariable) {
        this.getResponseVariable().setInnerText(responseVariable);
    }

    @Override
    public void setNtree(String numberTrees) {
        this.getNtree().setInnerText(numberTrees);
    }

    @Override
    public void setMtry(String mtry) {
        this.getMtry().setInnerText(mtry);
    }

    @Override
    public void setRowsSkipped(String rows) {
        this.getRowsSkipped().setInnerText(rows);
    }

    @Override
    public void setRows(String rows) {
        this.getRows().setInnerText(rows);
    }

    @Override
    public void setMatrixType(String matrixType) {
        this.getMatrixType().setInnerText(matrixType);
    }

    @Override
    public void setTreesGenerated(String treesGenerated) {
        this.getTreesGenerated().setInnerText(treesGenerated);
    }

    @Override
    public void setLeavesMin(String leavesMin) {
        this.getLeavesMin().setInnerText(leavesMin);
    }

    @Override
    public void setLeavesMean(String leavesMean) {
        this.getLeavesMean().setInnerText(leavesMean);
    }

    @Override
    public void setLeavesMax(String leavesMax) {
        this.getLeavesMax().setInnerText(leavesMax);
    }

    @Override
    public void setDepthMin(String depthMin) {
        this.getDepthMin().setInnerText(depthMin);
    }

    @Override
    public void setDepthMean(String depthMean) {
        this.getDepthMean().setInnerText(depthMean);
    }

    @Override
    public void setDepthMax(String depthMax) {
        this.getDepthMax().setInnerText(depthMax);
    }

    @Override
    public void setMatrixHeaders(List<String> headers) {
        // TODO: we really only need to set the labels once...
        //       repeatedly setting them might cause some flickering
        FlexTable matrixTable = getMatrixTable();
        matrixTable.setText(0, 0, "Actual/Predicted");

        for (int x = 0; x < headers.size(); x++) {
            matrixTable.setText(0, x + 1, headers.get(x));
        }
        matrixTable.setText(0, headers.size() + 1, "Errors");

        for (int x = 0; x < headers.size(); x++) {
            matrixTable.setText(x + 1, 0, headers.get(x));
        }
        matrixTable.setText(headers.size() + 1, 0, "Totals");

        matrixTable.getRowFormatter().setStylePrimaryName(0,
                getStyle().tableHeader());
        matrixTable.getColumnFormatter().setStylePrimaryName(0,
                getStyle().tableHeader());
    }

    @Override
    public void setMatrixScores(List<List<Integer>> scores) {
        // TODO: need to set errors/totals somewhere!
        for (int i = 0; i < scores.size(); i++) {
            List<Integer> row = scores.get(i);
            for (int j = 0; j < row.size(); j++) {
                getMatrixTable().setText(i + 1, j + 1, row.get(j).toString());
                if (i == j) {
                    getMatrixTable().getCellFormatter().setStylePrimaryName(
                            i + 1, j + 1, getStyle().cfmatrixHighlight());
                }
            }
        }
    }

    @Override
    public void setErrors(List<String> errors) {
        int col = getMatrixTable().getCellCount(0) - 1;
        int row = 1;
        for (String e : errors) {
            getMatrixTable().setText(row, col, e);
            row++;
        }
    }

    @Override
    public void setTotals(List<String> totals) {
        int col = 1;
        int row = getMatrixTable().getRowCount() - 1;
        for (String t : totals) {
            getMatrixTable().setText(row, col, t);
            col++;
        }
    }
}
