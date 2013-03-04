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
    private ConfusionMatrix confusion_matrix;
    private RFViewTreeProperties trees;
    private ResponseStatus response;


    public ResponseStatus getResponse()
    {
        return response;
    }

    @Override
    public String toString()
    {
        return "RFView{" + "data_key=" + data_key + ", model_key=" + model_key + ", response_variable=" + response_variable + ", ntree=" + ntree + ", mtry=" + mtry + ", confusion_key=" + confusion_key + ", confusion_matrix=" + confusion_matrix + ", trees=" + trees + ", response=" + response + '}';
    }

}
