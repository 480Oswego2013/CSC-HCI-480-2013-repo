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

package edu.oswego.csc480_hci521_2013.client.presenters;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ConfusionMatrix;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import java.util.List;

public interface ConfusionMatrixPresenter {

    void setData(RFView data);

    View getView();

    public abstract class View extends Composite implements ConfusionMatrixView {

        public abstract Element getProgress();
        public abstract Element getNtree();
        public abstract Element getMtry();
        public abstract Element getMatrixType();
        
        @Override
        public void setProgress(String progressUpdate) {
            this.getProgress().setInnerText(progressUpdate);
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
        public void setMatrixType(String matrixType) {
            this.getMatrixType().setInnerText(matrixType);
        }
//        public String getTreesGenerated() {
//            return treesGenerated.getInnerText();
//        }
//
//        private void updateConfusionMatrix(ConfusionMatrix confusionMatrix) {
//
//            // NOTE: sometimes it is not included in the response, maybe because it hasnt changed? not sure why
//            if (confusionMatrix == null) {
//                return;
//            }
//            type.setInnerText(confusionMatrix.getType());
//            rows.setInnerText(confusionMatrix.getRows() + "/" + confusionMatrix.getRowsSkipped());
//            classificationError.setInnerText(Double.toString(confusionMatrix.getClassificationError()));
//
//            List<String> header = confusionMatrix.getHeader();
//
//            // TODO: we really only need to set the labels once...
//            matrixTable.setText(0, 0, "Actual/Predicted");
//
//            /* Set Column Labels */
//            for (int x = 0; x < header.size(); x++) {
//                matrixTable.setText(0, x + 1, header.get(x));
//            }
//            matrixTable.setText(0, header.size() + 1, "Error");
//
//            /* Set Row Labels */
//            for (int x = 0; x < header.size(); x++) {
//                matrixTable.setText(x + 1, 0, header.get(x));
//            }
//            matrixTable.setText(header.size() + 1, 0, "Totals");
//
//            updateConfusionScores(confusionMatrix.getScores());
//            matrixTable.getRowFormatter().setStylePrimaryName(0, style.tableHeader());
//            matrixTable.getColumnFormatter().setStylePrimaryName(0, style.tableHeader());
//        }
//
//        private void updateConfusionScores(List<List<Integer>> scores) {
//            /* For All Header Values, Position Offset=1 */
//            int[] totals = new int[scores.size()];
//            for (int i = 0; i < scores.size(); i++) {
//                List<Integer> row = scores.get(i);
//                int total = 0;
//                int errors = 0;
//                for (int j = 0; j < row.size(); j++) {
//                    matrixTable.setText(i + 1, j + 1, row.get(j).toString());
//                    if (i == j) {
//                        matrixTable.getCellFormatter().setStylePrimaryName(i + 1, j + 1, style.cfmatrixHighlight());
//                    }
//                    totals[j] += row.get(j);
//                    if (i != j) {
//                        errors += row.get(j);
//                    }
//                    total += row.get(j);
//                }
//                // TODO: add the scores for the errors.
//                matrixTable.setText(i + 1, row.size() + 1, errors + "/" + total);
//            }
//            for (int i = 0; i < totals.length; i++) {
//                matrixTable.setText(scores.size() + 1, i + 1, Integer.toString(totals[i]));
//            }
//        }
    }
}
