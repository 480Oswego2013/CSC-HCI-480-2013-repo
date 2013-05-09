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
package edu.oswego.csc480_hci521_2013.client.presenters.adapters;

import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfusionMatrixAdapter {

    private final String ProgressComplete = "";
    private final String ProgressStopped = "0";
    private RFView rfView;
    private RFBuilder modelParameters;

    public ConfusionMatrixAdapter(RFView data, RFBuilder modelParameters) {
        this.rfView = data;
        this.modelParameters = modelParameters;
    }

    public String getProgress() {
        if (this.rfView.getResponse().isPoll()) {
            double completedTrees = (double) this.rfView.getTrees().getNumberBuilt();
            double totalTrees = (double) this.rfView.getNtree();

            if (totalTrees == 0) {
                return this.ProgressStopped;
            }

            Double percent = (completedTrees * 100) / totalTrees;
            return percent.toString();
        }

        return ProgressComplete;
    }

    public List<String> getHeaders() {
        if (rfView.getConfusionMatrix() != null
                && rfView.getConfusionMatrix().getHeader() != null) {
            return Arrays.asList(rfView.getConfusionMatrix().getHeader());
        }
        return new ArrayList<String>();
    }

    public List<List<Integer>> getScores() {
        List<List<Integer>> scores = new ArrayList<List<Integer>>();
        if (rfView.getConfusionMatrix() != null
                && rfView.getConfusionMatrix().getScores() != null) {
            for (Integer[] row : rfView.getConfusionMatrix().getScores()) {
                scores.add(Arrays.asList(row));
            }
        }
        return scores;
    }

    public List<String> getTotals() {
        List<List<Integer>> scores = getScores();
        int[] totals = new int[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            List<Integer> row = scores.get(i);
            for (int j = 0; j < row.size(); j++) {
                totals[j] += row.get(j);
            }
        }
        List<String> labels = new ArrayList<String>();
        for (int i = 0; i < totals.length; i++) {
            labels.add(Integer.toString(totals[i]));
        }
        return labels;
    }

    public List<String> getErrors() {
        List<String> labels = new ArrayList<String>();
        List<List<Integer>> scores = getScores();
        int overall = 0;
        int[] totals = new int[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            List<Integer> row = scores.get(i);
            int total = 0;
            int errors = 0;
            for (int j = 0; j < row.size(); j++) {
                totals[j] += row.get(j);
                if (i != j) {
                    errors += row.get(j);
                }
                total += row.get(j);
            }
            // TODO: add the scores for the errors.
            labels.add(errors + "/" + total);
            overall += errors;
        }
        int allTotals = 0;
        for (int i = 0; i < totals.length; i++) {
            allTotals += totals[i];
        }
        labels.add(overall + "/" + allTotals);
        return labels;
    }

    public String getClassificationError() {
        if (rfView.getConfusionMatrix() != null) {
            return Double.toString(rfView.getConfusionMatrix().getClassificationError());
        }
        return null;
    }

    public String getRowsSkipped() {
        if (rfView.getConfusionMatrix() != null) {
            return Integer.toString(rfView.getConfusionMatrix().getRowsSkipped());
        }
        return null;
    }

    public String getRows() {
        if (rfView.getConfusionMatrix() != null) {
            return Integer.toString(rfView.getConfusionMatrix().getRows());
        }
        return null;
    }

    public String getResponseVariable() {
        return this.modelParameters.getResponseVariable();
    }

    public String getNtree() {
        return Integer.toString(this.rfView.getNtree());
    }

    public String getMtry() {

        if (this.rfView.getMtry() < 0) {
            return "All";
        }

        return Integer.toString(this.rfView.getMtry());
    }

    public String getTreesBuilt() {
        return Integer.toString(this.rfView.getTrees().getNumberBuilt());
    }

    public String getLeavesMin() {
        if (rfView.getTrees().getLeaves() != null) {
            return Double.toString(this.rfView.getTrees().getLeaves().getMin());
        }
        return null;
    }

    public String getLeavesMean() {
        if (rfView.getTrees().getLeaves() != null) {
            return Double.toString(this.rfView.getTrees().getLeaves().getMean());
        }
        return null;
    }

    public String getLeavesMax() {
        if (rfView.getTrees().getLeaves() != null) {
           return Double.toString(this.rfView.getTrees().getLeaves().getMax());
        }
        return null;
    }

    public String getDepthMin() {
        if (rfView.getTrees().getDepth() != null) {
            return Double.toString(this.rfView.getTrees().getDepth().getMin());
        }
        return null;
    }

    public String getDepthMean() {
        if (rfView.getTrees().getDepth() != null) {
            return Double.toString(this.rfView.getTrees().getDepth().getMean());
        }
        return null;
    }

    public String getDepthMax() {
        if (rfView.getTrees().getDepth() != null) {
            return Double.toString(this.rfView.getTrees().getDepth().getMax());
        }
        return null;
    }

    public String getMatrixType() {
        if (rfView.getConfusionMatrix() != null) {
            return this.rfView.getConfusionMatrix().getType();
        }
        return null;
    }
}
