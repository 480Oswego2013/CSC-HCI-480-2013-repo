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
package edu.oswego.csc480_hci521_2013.client.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import edu.oswego.csc480_hci521_2013.client.presenters.RfParametersPresenter;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ColumnEnumValues;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect.Column;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.ColumnEnumValuesBuilder;
import edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders.RFBuilder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Michael Hayes
 * TODO: Need better way to handle other default value such as number of trees.
 * TODO: Input sanity checks such as numTrees is an integer.
 */
public class RfParametersViewImpl extends PopupPanel implements RfParametersPresenter.View {
    
    static final Logger logger = Logger.getLogger(RfParametersPresenter.View.class.getName());
    
    interface Style extends CssResource {
        String error();
    }
    
    interface Binder extends UiBinder<Widget, RfParametersViewImpl> {}
    private static Binder uiBinder = GWT.create(Binder.class);
    private RfParametersPresenter presenter;
    private List<String> columnHeaders; //Headers of the data columns from the data source.
    private Column[] columns; //Column definitions from the data source;
    
    @UiField ListBox classVars;
    @UiField IntegerBox numTrees;
    @UiField ListBox ignoreCols;
    @UiField Button submit;
    @UiField Button cancel;
    @UiField Label errorLabel;
    @UiField ScrollPanel classWeightsScrollPanel;
    @UiField FlexTable classWeights;

    
    public RfParametersViewImpl() {
        setWidget(uiBinder.createAndBindUi(this));
    }
    
    @Override
    public void buildUi() {
    }
    
    //Called when classVars selection is changed.
    @UiHandler("classVars")
    public void onChange(ChangeEvent event){
        int selectedIndex = classVars.getSelectedIndex();
        String selectedName = classVars.getValue(selectedIndex);
        setIgnoreColumns(selectedName);
        setClassWeights(selectedName);
    }
    
    @UiHandler("submit")
    public void onSubmitClick(ClickEvent event){
        RFBuilder builder = new RFBuilder(presenter.getDataKey());
        
        //TODO: Should probably alert the user instead of defaulting to 50.
        if(numTrees == null || numTrees.getValue() == null || numTrees.getValue() <= 0)
            builder.setNtree(50);
        else
            builder.setNtree(numTrees.getValue());
        
        int classVarSelected = classVars.getSelectedIndex();
        String classVarVal = classVars.getValue(classVarSelected);
        builder.setResponseVariable(classVarVal);
        for(int i = 0; i < ignoreCols.getItemCount(); i++){
            if(ignoreCols.isItemSelected(i)){
                Integer ignoreThis = Integer.valueOf(ignoreCols.getValue(i));
                builder.setIgnore(ignoreThis);
            }
        }

        //Set class weights into builder
        //Start at row index 1 since first row is label info.
        HashMap<String, Double> values = new HashMap<String,Double>();
        for(int row = 1; row < classWeights.getRowCount(); row++){
            String label = classWeights.getText(row, 0);
            DoubleBox value = (DoubleBox) classWeights.getWidget(row,1);
            values.put(label,value.getValue());
        };
        builder.setClassWeights(values);

        presenter.fireRFParameterEvent(builder);
        //this.hide();
    }
    
    @UiHandler("cancel")
    public void onCancelClick(ClickEvent event){
        this.hide();
    }
    
    @Override
    public void setHeaders(List<String> headers){
        this.columnHeaders = headers;
        
        //Only add a selectable header if it is an int or enum column.
        //If it is an int, must be >=2 and <= 254
        for(int i = 0 ; i < columns.length ; i++){
            if((columns[i].getType().equals("int") 
                    && columns[i].getMin() >= 2
                    && columns[i].getMax() <= 254) ||
                    ( columns[i].getType().equals("enum")
                    && columns[i].getEnumDomainSize() >= 2
                    && columns[i].getEnumDomainSize() <= 254)) {
                classVars.addItem(columns[i].getName());
            }
        }
        
        //Select the first valid response column
        //Setup the ignore columns
        //Setup the class weights.
        for(int i = 0; i < columns.length; i++){
            if((columns[i].getType().equals("int")
                    && columns[i].getMin() >= 2
                    && columns[i].getMax() <= 254) ||
                    ( columns[i].getType().equals("enum")
                    && columns[i].getEnumDomainSize() >= 2
                    && columns[i].getEnumDomainSize() <= 254)) {
                setClassWeights(columns[i].getName());
                setIgnoreColumns(columns[i].getName());
                break;
            }
        }
        classVars.setItemSelected(0,true);
    }
    
    //Set the column definition info.
    //Calls setHeaders(...)
    @Override
    public void setColumnInfo(Column[] cols){
        this.columns = cols;
        List<String> headers = new ArrayList<String>();
        for(int i = 0 ; i < columns.length ; i++){
            headers.add(columns[i].getName());
        }
        setHeaders(headers);
    }
    
    //Setup the class weights selections corresponding with the selected
    //response variable.
    public void setClassWeights(String selectedVariableName){
        Column selected = null;
        int columnNumber = 0;
        for(Column col : columns){
            if(col.getName().equals(selectedVariableName)){
                selected = col;
                break;
            }
            columnNumber++;
        }

        if(selected != null){
            classWeights = new FlexTable();
            classWeightsScrollPanel.clear();
            classWeightsScrollPanel.add(classWeights);
            classWeights.setText(0,0,"Class");
            classWeights.setText(0,1,"Weight");
            ColumnEnumValuesBuilder builder;
            builder = new ColumnEnumValuesBuilder(presenter.getDataKey(), columnNumber);
            //Call the GWT RPC service to get the column values.
            presenter.getH2OService().getColumnEnumValues(builder, 
                new AsyncCallback<ColumnEnumValues>() {
                    @Override                    
                    public void onFailure(Throwable thrwbl) {
                        logger.log(Level.SEVERE, thrwbl.toString());
                        setError(thrwbl.getMessage());
                    }

                    @Override
                    public void onSuccess(final ColumnEnumValues vals) {
                        String[] values = vals.getValues();
                        for(int i = 0; i < values.length; i++){
                            classWeights.setText(i, 0, values[i]);
                            DoubleBox inputBox = new DoubleBox();
                            inputBox.setValue(1.0);
                            inputBox.setWidth("50px");
                            classWeights.setWidget(i, 1, inputBox);
                        }
                    }
                }
            );
            
        } else {
            throw new NullPointerException("Could not find selected classification variable column.");
        }
    }
    
    //Set the available ignore columns to everything excepted the selected class var
    public void setIgnoreColumns(String selected){
        ignoreCols.clear();
        //for(String column : columnHeaders){
        for(int i = 0; i < columnHeaders.size(); i++){
            String column = columnHeaders.get(i);
            if(!column.equals(selected))
                ignoreCols.addItem(column, Integer.toString(i));
        }
        ignoreCols.setVisibleItemCount(columnHeaders.size());
    }
    
    @Override
    public void showPopUp(){
        final PopupPanel me = this;
        this.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            public void setPosition(int offsetWidth, int offsetHeight){
                int left = 100;
                int top = 140;
                me.setPopupPosition(left, top);
            }
        });
    }
    
    @Override
    public void hidePopup(){
        this.hide();
    }
    
    @Override
    public void setError(String error){
        errorLabel.setText(error);
    }
    
    @Override
    public void setPresenter(RfParametersPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }
}
