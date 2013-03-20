package edu.oswego.csc480_hci521_2013.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import edu.oswego.csc480_hci521_2013.client.presenters.adapters.ConfusionMatrixScore;
import java.util.List;

/**'
 *
 */
public abstract class AbstractConfusionMatrix extends Composite implements ConfusionMatrixView {

    @Override
    public void hideProgress() {
        getProgress().getStyle().setVisibility(com.google.gwt.dom.client.Style.Visibility.HIDDEN);
    }

    @Override
    public void setProgress(String progressUpdate) {
        this.getProgress().setAttribute("value", progressUpdate);
    }

    @Override
    public void setClassificationError(String classificationError) {
        this.getClassificationError().setInnerText(classificationError);
    }

    @Override
    public void setResponseVariable(String responseVariable) {
        this.getResponseVariable().setInnerText(responseVariable);
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
    public void setRowsSkipped(String rows) {
        this.getRowsSkipped().setInnerText(rows);
    }

    @Override
    public void setRows(String rows) {
        this.getRows().setInnerText(rows);
    }

    @Override
    public void setMatrixType(String matrixType) {
        this.getMatrixType().setInnerText(matrixType);
    }

    @Override
    public void setTreesGenerated(String treesGenerated) {
        this.getTreesGenerated().setInnerText(treesGenerated);
    }

    @Override
    public void setLeavesMin(String leavesMin) {
        this.getLeavesMin().setInnerText(leavesMin);
    }

    @Override
    public void setLeavesMean(String leavesMean) {
        this.getLeavesMean().setInnerText(leavesMean);
    }

    @Override
    public void setLeavesMax(String leavesMax) {
        this.getLeavesMax().setInnerText(leavesMax);
    }

    @Override
    public void setDepthMin(String depthMin) {
        this.getDepthMin().setInnerText(depthMin);
    }

    @Override
    public void setDepthMean(String depthMean) {
        this.getDepthMean().setInnerText(depthMean);
    }

    @Override
    public void setDepthMax(String depthMax) {
        this.getDepthMax().setInnerText(depthMax);
    }

    @Override
    public void setMatrixHeaders(List<String> headers) {
        // TODO: we really only need to set the labels once...
        //       repeatedly setting them might cause some flickering
        FlexTable matrixTable = getMatrixTable();
        matrixTable.setText(0, 0, "Actual/Predicted");

        for (int x = 0; x < headers.size(); x++) {
            matrixTable.setText(0, x + 1, headers.get(x));
        }
        matrixTable.setText(0, headers.size() + 1, "Error");

        for (int x = 0; x < headers.size(); x++) {
            matrixTable.setText(x + 1, 0, headers.get(x));
        }
        matrixTable.setText(headers.size() + 1, 0, "Totals");

        matrixTable.getRowFormatter().setStylePrimaryName(0,
                getStyle().tableHeader());
        matrixTable.getColumnFormatter().setStylePrimaryName(0,
                getStyle().tableHeader());
    }

    @Override
    public void setMatrixScores(List<ConfusionMatrixScore> scores) {
        // TODO: need to set errors/totals somewhere!
        for (ConfusionMatrixScore score : scores) {
            this.getMatrixTable().setText(
                    score.getPositionX(),
                    score.getPositionY(),
                    Integer.toString(score.getScore()));
            if (score.getPositionX() == score.getPositionY()) {
                getMatrixTable().getCellFormatter().setStylePrimaryName(
                        score.getPositionX(), score.getPositionY(),
                        getStyle().cfmatrixHighlight());
            }
        }
    }
}
