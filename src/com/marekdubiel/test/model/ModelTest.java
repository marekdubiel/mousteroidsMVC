package com.marekdubiel.test.model;

import com.marekdubiel.main.model.MainUpdateLoopAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ModelTest {

    @Test
    void TestUpdaterShouldBeUpdatedFpsTimesPerSecond(){
        int fps = 50;

        UpdateTest testUpdater = new UpdateTest();
        Thread testLoopThread = new Thread(new MainUpdateLoopAction(fps,testUpdater));
        testUpdater.setRuning(true);
        testLoopThread.start();

        if(System.currentTimeMillis()>1000){
            assertEquals(fps,testUpdater.getFrameCount());
        }
        testUpdater.setRuning(false);

    }
}
