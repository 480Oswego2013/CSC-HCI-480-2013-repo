package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenter;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ConfusionMatrix;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import java.util.List;

/**
 *
 */
public class ConfusionMatrixViewImpl extends Composite implements ConfusionMatrixPresenter.View {

    private ConfusionMatrixPresenter presenter;
    private FlowPanel mainFrame;
    private FlexTable matrixTable;
    private Grid treesTable;
    private Label treesGenerated = new Label();
    private Element progress;
    private Anchor progressAnchor;

    private Label type = new Label();
    private Label responseVariable = new Label();
    private Label ntree = new Label();
    private Label mtry = new Label();
    private Label rows = new Label();
    private Label classificationError = new Label();

    public ConfusionMatrixViewImpl() {
    }

    @Override
    public void buildUi() {
        mainFrame = new FlowPanel();
        // TODO: figure out what makes sense for layout
        mainFrame.setSize("100%", "100%");

        progress = DOM.createElement("progress");
        progress.setAttribute("max", "100");
        progress.setAttribute("value", "0");
        progressAnchor = Anchor.wrap(progress);
        mainFrame.add(progressAnchor);

        // TODO: add more widgets
        addPair(new Label("Confusion Matrix - "), type);

        addPair(new Label("Response Variable:"), responseVariable);
        addPair(new Label("Number of Trees:"), ntree);
        addPair(new Label("mtry:"), mtry);
        addPair(new Label("Rows Used/Skipped:"), rows);
        addPair(new Label("Classification Error:"), classificationError);

        matrixTable = new FlexTable();
        mainFrame.add(matrixTable);

        mainFrame.add(new HTML("Trees"));

        addPair(new Label("Trees Generated:"), treesGenerated);

        treesTable = new Grid(3, 4);
        treesTable.setText(0, 1, "Min");
        treesTable.setText(0, 2, "Mean");
        treesTable.setText(0, 3, "Max");
        treesTable.setText(1, 0, "Leaves");
        treesTable.setText(2, 0, "Depth");
        mainFrame.add(treesTable);

        initWidget(mainFrame);
    }

    private void addPair(Label label, Label value) {
        // TODO: i dont want to use horizontal panel if i dont have to
        HorizontalPanel panel = new HorizontalPanel();
        panel.add(label);
        panel.add(value);
        mainFrame.add(panel);
    }

    @Override
    public void setData(RFView data) {
        if (data.getResponse().isPoll()) {
            Double percent = ((double)data.getTrees().getNumberBuilt() * 100) / (double)data.getNtree();
            progress.setAttribute("value", percent.toString());
        }
        else {
            mainFrame.remove(progressAnchor);
        }

        responseVariable.setText(Integer.toString(data.getResponseVariable()));
        ntree.setText(Integer.toString(data.getNtree()));
        mtry.setText(Integer.toString(data.getMtry()));

        updateConfusionMatrix(data.getConfusionMatrix());

        treesGenerated.setText(Integer.toString(data.getTrees().getNumberBuilt()));

        treesTable.setText(1, 1, Double.toString(data.getTrees().getLeaves().getMin()));
        treesTable.setText(1, 2, Double.toString(data.getTrees().getLeaves().getMean()));
        treesTable.setText(1, 3, Double.toString(data.getTrees().getLeaves().getMax()));
        treesTable.setText(2, 1, Double.toString(data.getTrees().getDepth().getMin()));
        treesTable.setText(2, 2, Double.toString(data.getTrees().getDepth().getMean()));
        treesTable.setText(2, 3, Double.toString(data.getTrees().getDepth().getMax()));
    }

    @Override
    public void setPresenter(ConfusionMatrixPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    private void updateConfusionMatrix(ConfusionMatrix confusionMatrix) {
        // NOTE: sometimes it is not included in the response, maybe because it hasnt changed? not sure why
        if (confusionMatrix == null) {
            return;
        }
        type.setText(confusionMatrix.getType());
        rows.setText(confusionMatrix.getRows() + "/" + confusionMatrix.getRowsSkipped());
    	classificationError.setText(Double.toString(confusionMatrix.getClassificationError()));

        String[] header = confusionMatrix.getHeader();

        // TODO: we really only need to set the labels once...
        matrixTable.setText(0, 0, "Actual/Predicted");
        /* Set Column Labels */
        for (int x = 0; x < header.length; x++) {
            matrixTable.setText(0, x + 1, header[x]);
        }
        matrixTable.setText(0, header.length + 1, "Error");

        /* Set Row Labels */
        for (int x = 0; x < header.length; x++) {
            matrixTable.setText(x + 1, 0, header[x]);
        }
        matrixTable.setText(header.length + 1, 0, "Totals");

        updateConfusionScores(confusionMatrix.getScores());
    }

    private void updateConfusionScores(Integer[][] scores) {
        /* For All Header Values, Position Offset=1 */
        // TODO: errors...
        int[] totals = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            Integer[] row = scores[i];
            int total = 0;
            int errors = 0;
            for (int j = 0; j < row.length; j++) {
                matrixTable.setText(i + 1, j + 1, row[j].toString());
                totals[j] += row[j];
                if (i != j) {
                    errors += row[j];
                }
                total += row[j];
            }
            // TODO: add the scores for the errors.
            matrixTable.setText(i + 1, row.length + 1, errors + "/" + total);
        }
        for (int i = 0; i < totals.length; i++) {
            matrixTable.setText(scores.length + 1, i + 1, Integer.toString(totals[i]));
        }
    }
}
