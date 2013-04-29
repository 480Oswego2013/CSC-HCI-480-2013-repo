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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.IsWidget;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public interface DataPanelView extends IsWidget {

    void setGenerateCommand(Scheduler.ScheduledCommand command);

    void setData(List<Map<String, String>> data);

    void setColumns(Set<String> columns);

    void setPresenter(DataPanelPresenter presenter);

    void forestStarted();

    void setForestStatus(int done, int total);

    void forestFinish(int count);
    
    void showPopinButton(boolean show);
}
