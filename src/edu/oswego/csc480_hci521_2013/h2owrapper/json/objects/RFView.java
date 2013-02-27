package edu.oswego.csc480_hci521_2013.h2owrapper.json.objects;

/**
 *
 */
public class RFView
{
    private String data_key;
    private String model_key;
    private int response_variable;
    private int ntree;
    private int mtry;
    private String confusion_key;

    // TODO: finish deserialization
//  "confusion_matrix" : {
//    "type" : "full scoring",
//    "classification_error" : 0.0,
//    "rows_skipped" : 0,
//    "rows" : 150,
//    "header" : [ "Iris-setosa", "Iris-versicolor", "Iris-virginica" ],
//    "scores" : [ [ 50, 0, 0 ], [ 0, 50, 0 ], [ 0, 0, 50 ] ],
//    "used_trees" : 50
//  },
//  "trees" : {
//    "number_built" : 50,
//    "depth" : {
//      "min" : 4.0,
//      "mean" : 5.84,
//      "max" : 8.0
//    },
//    "leaves" : {
//      "min" : 5.0,
//      "mean" : 9.08,
//      "max" : 13.0
//    }
//  },
    private ResponseStatus response;


    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "RFView{" + "data_key=" + data_key + ", model_key=" + model_key + ", response_variable=" + response_variable + ", ntree=" + ntree + ", mtry=" + mtry + ", confusion_key=" + confusion_key + ", response=" + response + '}';
    }
}
