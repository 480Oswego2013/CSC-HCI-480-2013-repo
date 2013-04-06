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
package edu.oswego.csc480_hci521_2013.shared.h2o.json;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.List;

public class ConfusionMatrix implements IsSerializable
{

/* Example JSON
  "confusion_matrix" : {
    "type" : "full scoring",
    "classification_error" : 0.0,
    "rows_skipped" : 0,
    "rows" : 150,
    "header" : [ "Iris-setosa", "Iris-versicolor", "Iris-virginica" ],
    "scores" : [ [ 50, 0, 0 ], [ 0, 50, 0 ], [ 0, 0, 50 ] ],
    "used_trees" : 50
  },
 */
    private String type;
    private float classification_error;
    private int rows_skipped;
    private int rows;
    private List<String> header;
    private List<List<Integer>> scores;
    private int used_trees;

    public String getType()
    {
        return type;
    }

    public float getClassificationError()
    {
        return classification_error;
    }

    public int getRowsSkipped()
    {
        return rows_skipped;
    }

    public int getRows()
    {
        return rows;
    }

    public List<String> getHeader()
    {
        return header;
    }

    public List<List<Integer>> getScores()
    {
        return scores;
    }

    public int getUsedTrees()
    {
        return used_trees;
    }

    @Override
    public String toString()
    {
        return "ConfusionMatrix{" + "type=" + type + ", classification_erro=" + classification_error+ ", rows_skipped=" + rows_skipped + ", rows=" + rows + ", header=" + header+ ", scores=" + scores+ ", used_trees=" + used_trees + '}';
    }
}
