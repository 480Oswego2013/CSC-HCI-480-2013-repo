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

import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfusionMatrixAdapter {

    private final String ProgressComplete = "";
    private final String ProgressStopped = "0";
    private RFView rfView;

    public ConfusionMatrixAdapter(RFView data) {
        this.rfView = data;
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

    public List<ConfusionMatrixScore> getScores() {
        ArrayList scores = new ArrayList();
        List<List<Integer>> matrixScores = this.rfView.getConfusionMatrix().getScores();

        for (int i = 0; i < matrixScores.size(); i++) {
            List<Integer> row = matrixScores.get(i);

            for (int j = 0; j < row.size(); j++) {
                scores.add(new ConfusionMatrixScoreImpl(i + 1, j + 1, row.get(j)));
            }
        }
        return scores;
    }

    public String getResponseVariable() {
        return Integer.toString(this.rfView.getResponseVariable());
    }

    public String getNtree() {
        return Integer.toString(this.rfView.getNtree());
    }

    public String getMtry() {
        
        if (this.rfView.getMtry() < 0)
        {
            return "All";
        }
        
        return Integer.toString(this.rfView.getMtry());
    }

    //updateConfusionMatrix(data.getConfusionMatrix());
    public String getTreesBuilt() {
        return Integer.toString(this.rfView.getTrees().getNumberBuilt());
    }

    public String getLeavesMin() {
        return Double.toString(this.rfView.getTrees().getLeaves().getMin());
    }

    public String getLeavesMean() {
        return Double.toString(this.rfView.getTrees().getLeaves().getMean());
    }

    public String getLeavesMax() {
        return Double.toString(this.rfView.getTrees().getLeaves().getMax());
    }

    public String getDepthMin() {
        return Double.toString(this.rfView.getTrees().getDepth().getMin());
    }

    public String getDepthMean() {
        return Double.toString(this.rfView.getTrees().getDepth().getMean());
    }

    public String getDepthMax() {
        return Double.toString(this.rfView.getTrees().getDepth().getMax());
    }

    public String getMatrixType() {
        return this.rfView.getConfusionMatrix().getType();
    }
}
