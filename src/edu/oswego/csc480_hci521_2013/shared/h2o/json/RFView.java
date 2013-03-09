package edu.oswego.csc480_hci521_2013.shared.h2o.json;

/**
 *
 */
public class RFView extends H2OResponse {

    private String data_key;
    private String model_key;
    private int response_variable;
    private int ntree;
    private int mtry;
    private String confusion_key;
    private ConfusionMatrix confusion_matrix;
    private TreeProperties trees;

    public String getDataKey() {
        return data_key;
    }

    public String getModelKey() {
        return model_key;
    }

    public int getResponseVariable() {
        return response_variable;
    }

    public int getNtree() {
        return ntree;
    }

    public int getMtry() {
        return mtry;
    }

    public String getConfusionKey() {
        return confusion_key;
    }

    public ConfusionMatrix getConfusionMatrix() {
        return confusion_matrix;
    }

    public TreeProperties getTrees() {
        return trees;
    }

    @Override
    public String toString() {
        return "RFView{" + "data_key=" + data_key + ", model_key=" + model_key + ", response_variable=" + response_variable + ", ntree=" + ntree + ", mtry=" + mtry + ", confusion_key=" + confusion_key + ", confusion_matrix=" + confusion_matrix + ", trees=" + trees + '}';
    }
}
