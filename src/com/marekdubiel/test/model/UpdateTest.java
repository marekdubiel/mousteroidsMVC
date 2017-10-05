package com.marekdubiel.test.model;

import com.marekdubiel.main.model.Updatable;

public class UpdateTest implements Updatable{
    boolean runing;
    public int frameCount = 0;

    @Override
    public void update(double delta){
        frameCount+=1;
    }

    @Override
    public boolean getRuning() {
        return runing;
    }

    public void setRuning(boolean runing){
        this.runing = runing;
    }

    public int getFrameCount() {
        return frameCount;
    }
}
