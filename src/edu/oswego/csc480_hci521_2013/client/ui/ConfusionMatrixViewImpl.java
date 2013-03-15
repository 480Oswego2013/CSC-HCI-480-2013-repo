package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
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

    interface Style extends CssResource {
        String tableHeader();
        String cfmatrixHighlight();
    }

    interface Binder extends UiBinder<Widget, ConfusionMatrixViewImpl> {}
    private static Binder uiBinder = GWT.create(Binder.class);

    private ConfusionMatrixPresenter presenter;

    @UiField Style style;

    @UiField Element progress;
    @UiField FlexTable matrixTable;
    @UiField Grid treesTable;
    @UiField Element treesGenerated;
    @UiField Element type;
    @UiField Element responseVariable;
    @UiField Element ntree;
    @UiField Element mtry;
    @UiField Element rows;
    @UiField Element classificationError;

    public ConfusionMatrixViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void buildUi() {
    }

    @Override
    public void setData(RFView data) {
        if (data.getResponse().isPoll()) {
            Double percent = ((double)data.getTrees().getNumberBuilt() * 100) / (double)data.getNtree();
            progress.setAttribute("value", percent.toString());
        }
        else {
            progress.removeFromParent();
        }

        responseVariable.setInnerText(Integer.toString(data.getResponseVariable()));
        ntree.setInnerText(Integer.toString(data.getNtree()));
        mtry.setInnerText(Integer.toString(data.getMtry()));

        updateConfusionMatrix(data.getConfusionMatrix());

        treesGenerated.setInnerText(Integer.toString(data.getTrees().getNumberBuilt()));

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
        type.setInnerText(confusionMatrix.getType());
        rows.setInnerText(confusionMatrix.getRows() + "/" + confusionMatrix.getRowsSkipped());
    	classificationError.setInnerText(Double.toString(confusionMatrix.getClassificationError()));

        List<String> header = confusionMatrix.getHeader();

        // TODO: we really only need to set the labels once...
        matrixTable.setText(0, 0, "Actual/Predicted");

        /* Set Column Labels */
        for (int x = 0; x < header.size(); x++) {
            matrixTable.setText(0, x + 1, header.get(x));
        }
        matrixTable.setText(0, header.size() + 1, "Error");

        /* Set Row Labels */
        for (int x = 0; x < header.size(); x++) {
            matrixTable.setText(x + 1, 0, header.get(x));
        }
        matrixTable.setText(header.size() + 1, 0, "Totals");

        updateConfusionScores(confusionMatrix.getScores());
        matrixTable.getRowFormatter().setStylePrimaryName(0, style.tableHeader());
        matrixTable.getColumnFormatter().setStylePrimaryName(0, style.tableHeader());
    }

    private void updateConfusionScores(List<List<Integer>> scores) {
        /* For All Header Values, Position Offset=1 */
        int overall=0;
        int[] totals = new int[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            List<Integer> row = scores.get(i);
            int total = 0;
            int errors = 0;
            for (int j = 0; j < row.size(); j++) {
                matrixTable.setText(i + 1, j + 1, row.get(j).toString());
                if (i == j) {
                    matrixTable.getCellFormatter().setStylePrimaryName(i + 1, j + 1, style.cfmatrixHighlight());
                }
                totals[j] += row.get(j);
                if (i != j) {
                    errors += row.get(j);
                }
                total += row.get(j);
            }
            // TODO: add the scores for the errors.
            matrixTable.setText(i + 1, row.size() + 1, errors + "/" + total);
            overall += errors;
        }
        int allTotals = 0;
        for (int i = 0; i < totals.length; i++) {
            matrixTable.setText(scores.size() + 1, i + 1, Integer.toString(totals[i]));
            allTotals += totals[i];
        }
        /* Error Average Total */
        String val = String.valueOf(overall) + "/" + allTotals;
        matrixTable.setText(scores.size() + 1, totals.length + 1, val);
    }
}
