package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;
import java.util.List;

public class ConfusionMatrix
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
