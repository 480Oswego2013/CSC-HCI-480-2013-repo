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

package edu.oswego.csc480_hci521_2013.client.ui;

import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ConfusionMatrix;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView.TreeProperties.MinMeanMax;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Patrick
 */
public class ConfusionMatrixPresenterTest {

    @Mock
    private RFView randomForest;
    @Mock
    private ConfusionMatrixView matrixView;
    @Mock
    private ResponseStatus status;
    @Mock
    private RFView.TreeProperties tree;
    @Mock
    private ConfusionMatrix matrix;
    @Mock
    private MinMeanMax treeFeaturesLeaves;
    @Mock
    private MinMeanMax treeFeaturesDepth;

    public ConfusionMatrixPresenterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(randomForest.getResponse()).thenReturn(status);
        when(randomForest.getTrees()).thenReturn(tree);
        when(randomForest.getConfusionMatrix()).thenReturn(matrix);
        when(tree.getLeaves()).thenReturn(treeFeaturesLeaves);
        when(tree.getDepth()).thenReturn(treeFeaturesDepth);
    }

    @After
    public void tearDown() {
    }
    @Test
    public void test_total_trees() {
        final int treeCount = 1000;

        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setNtree("1000");
    }

    @Test
    public void test_no_progress() {
        final int treeCount = 0;

        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setProgress("0");
    }

    @Test
    public void test_some_progress() {
        final int treesBuilt = 50;
        final int treeCount = 100;

        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        when(tree.getNumberBuilt()).thenReturn(treesBuilt);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setProgress("50.0");
    }

    @Test
    public void test_total_progress() {
        final int treesBuilt = 100;
        final int treeCount = 100;

        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        when(tree.getNumberBuilt()).thenReturn(treesBuilt);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setProgress("100.0");
    }

    @Test
    public void test_trees() {
        final int treesBuilt = 100;
        final int treeCount = 100;

        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        when(tree.getNumberBuilt()).thenReturn(treesBuilt);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setNtree("100");
    }

    @Test
    public void test_mtry() {
        final int mtry = 7;

        when(randomForest.getMtry()).thenReturn(mtry);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setMtry("7");
    }

    @Test
    public void test_matrix_type() {
        final String matrixType = "unknown";

        when(randomForest.getConfusionMatrix()).thenReturn(matrix);
        when(matrix.getType()).thenReturn(matrixType);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        verify(matrixView).setMatrixType(matrixType);
    }

    @Test
    public void test_matrix() {
        List<List<Integer>> scores = new ArrayList<List<Integer>>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(1,0,0)),
                new ArrayList<Integer>(Arrays.asList(0,1,0)),
                new ArrayList<Integer>(Arrays.asList(0,0,1))
                ));

        when(matrix.getScores()).thenReturn(scores);
        when(randomForest.getConfusionMatrix()).thenReturn(matrix);

        ConfusionMatrixPresenterImpl.updateView(matrixView, randomForest);
        //verify(matrixView).setMatrixType(matrixType);
    }

}