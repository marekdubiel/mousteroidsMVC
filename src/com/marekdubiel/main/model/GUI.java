package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.controller.InputManager;

import java.util.ArrayList;

public class GUI {
    static GUI instance;
    SimpleObject[] hearts;
    PlayerObject player;

    public static GUI getInstance() {
        if(instance==null)
            instance = new GUI();
        return instance;
    }

    private GUI(){

    }

    public void initialize(){
        createCrosshair();
        createScore();
        createHearts();
    }

    public void linkPlayer(PlayerObject player){
        this.player = player;
    }

    private void createCrosshair(){
        SimpleObject crosshair = new SimpleObject(){
            public void update(double delta){
                setPosition(InputManager.getInstance().getFocusPoint());
            }
        };
        crosshair.initializeGeneralObject(InputManager.getInstance().getFocusPoint());
        crosshair.initializeImageObject("crosshair",9,0,1);
    }

    private void createScore(){
        Double2D scorePosition = Double2D.multiply(Settings.getInstance().centerPoint(),new Double2D(1,0.18));
        Score score = new Score(scorePosition);
    }

    private void createHearts(){
        int heartsAmount = Settings.getInstance().getLifeAmount();
        hearts = new SimpleObject[heartsAmount];
        for(int i=0;i<hearts.length;i++){
            hearts[i] = new SimpleObject();
            Double2D offset = new Double2D(0.4 + (i+1)*1.2/(hearts.length+1),1.8);
            hearts[i].initializeGeneralObject(Double2D.multiply(Settings.getInstance().centerPoint(),offset));
            hearts[i].initializeImageObject("heartFull",8, 0,2);
            ObjectManager.getInstance().addObject(hearts[i]);
        }
    }

    private void updateHearts(){
        if (player!=null) {
            if (player.getLife() != Settings.getInstance().getLifeAmount()) {
                for (int i = 0; i < hearts.length; i++) {
                    if (i+1 < player.getLife())
                        hearts[i].initializeImageObject("heartFull",8, 0,2);
                    else
                        hearts[i].initializeImageObject("heartEmpty",8, 0,2);

                }
            }
        }
    }

    public void update(){
        updateHearts();
    }
}
