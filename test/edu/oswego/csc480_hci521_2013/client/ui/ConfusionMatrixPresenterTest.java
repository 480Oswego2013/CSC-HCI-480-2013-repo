/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oswego.csc480_hci521_2013.client.ui;

import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixPresenterImpl;
import edu.oswego.csc480_hci521_2013.client.presenters.ConfusionMatrixView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ConfusionMatrix;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.RFView;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;
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
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void test_total_trees() {
        final int treeCount = 1000;
        
        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setNtree("1000");               
    }
    
    @Test
    public void test_no_progress() {
        final int treeCount = 0;
        
        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setProgress("0");               
    }
    
    @Test
    public void test_some_progress() {
        final int treesBuilt = 50;
        final int treeCount = 100;
        
        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        when(tree.getNumberBuilt()).thenReturn(treesBuilt);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setProgress("50.0");               
    }
    
    @Test
    public void test_total_progress() {
        final int treesBuilt = 100;
        final int treeCount = 100;
        
        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        when(tree.getNumberBuilt()).thenReturn(treesBuilt);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setProgress("100.0");               
    }
    
    @Test
    public void test_trees() {
        final int treesBuilt = 100;
        final int treeCount = 100;
        
        when(randomForest.getNtree()).thenReturn(treeCount);
        when(status.isPoll()).thenReturn(true);
        when(tree.getNumberBuilt()).thenReturn(treesBuilt);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setNtree("100");               
    }
    
    @Test
    public void test_mtry() {
        final int mtry = 7;
        
        when(randomForest.getMtry()).thenReturn(mtry);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setMtry("7");               
    }
    
    @Test
    public void test_matrix_type() {
        final String matrixType = "unknown";
        
        when(randomForest.getConfusionMatrix()).thenReturn(matrix);
        when(matrix.getType()).thenReturn(matrixType);
        
        ConfusionMatrixPresenterImpl.UpdateView(matrixView, randomForest);
        verify(matrixView).setMatrixType(matrixType);
    }
}