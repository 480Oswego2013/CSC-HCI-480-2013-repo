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

package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import edu.oswego.csc480_hci521_2013.client.activity.MenuActivity;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.EastPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.MenuActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.PopoutPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.activity.mapper.WestPanelActivityMapper;
import edu.oswego.csc480_hci521_2013.client.presenters.DataPanelPresenter;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceAsync;
import edu.oswego.csc480_hci521_2013.client.services.H2OServiceProvider;
import edu.oswego.csc480_hci521_2013.client.ui.BasicMenuView;
import edu.oswego.csc480_hci521_2013.client.ui.DataPanelViewImpl;
import edu.oswego.csc480_hci521_2013.client.ui.MenuView;
import edu.oswego.csc480_hci521_2013.client.ui.DoublePanelView;

public class AppGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(MenuView.class).to(BasicMenuView.class).in(Singleton.class);
        bind(MenuActivityMapper.class);
        bind(WestPanelActivityMapper.class);
        bind(EastPanelActivityMapper.class);
        bind(PopoutPanelActivityMapper.class);
        bind(H2OServiceAsync.class).toProvider(H2OServiceProvider.class).in(Singleton.class);
        bind(DataPanelPresenter.View.class).to(DataPanelViewImpl.class).in(Singleton.class);
    }    
}
