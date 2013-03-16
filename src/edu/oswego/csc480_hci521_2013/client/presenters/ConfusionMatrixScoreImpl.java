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

package edu.oswego.csc480_hci521_2013.client.presenters;

public class ConfusionMatrixScoreImpl implements ConfusionMatrixScore{
    
    private int xPosition;
    private int yPosition;
    private int score;
    
    public ConfusionMatrixScoreImpl(int x, int y, int score) {
        this.xPosition = x;
        this.yPosition = y;
        this.score = score;
    }

    @Override
    public int getPositionX() {
        return this.xPosition;
    }

    @Override
    public int getPositionY() {
        return this.yPosition;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
