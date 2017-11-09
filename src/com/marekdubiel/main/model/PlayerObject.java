package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.additional.Geometry;
import com.marekdubiel.main.controller.InputManager;
import com.marekdubiel.main.view.ImageSprite;
import com.marekdubiel.main.view.Sprite;

import static java.lang.System.currentTimeMillis;

public class PlayerObject extends CollidableObject {
    private int life;
    private SimpleObject jet;
    private boolean shooting;

    public PlayerObject(){
        initialize();
        initializeJet();
    }

    private void initialize(){
        super.initializeCollidableObject("spaceship", 6, Settings.getInstance().centerPoint(),90,1);
        setLife(Settings.getInstance().getLifeAmount());
        setMaxSpeed(15);
        setShooting(false);
        GUI.getInstance().linkPlayer(this);
    }

    private void initializeJet(){
        jet = new SimpleObject(){
            PlayerObject parent = PlayerObject.this;
            double distance = 25;
            @Override
            public void update(double delta){
                super.update(delta);
                setPosition(Calculate.pointByDistanceAndDirection(parent.getPosition(),parent.getRotation()-180,distance));
                setRotation(parent.getRotation());
            }
        };
        jet.initializeGeneralObject(getPosition());
        jet.initializeImageObject("jet",6,getRotation(),1.5);
    }

    public void update(double delta){
        super.update(delta);
        setDirectionAndSpeed();
        animateJet();
        shoot();
    }

    private void setDirectionAndSpeed(){
        setDirection(Calculate.direction(InputManager.getInstance().getFocusPoint(),getPosition()));
        double calculatedSpeed = Calculate.distance(InputManager.getInstance().getFocusPoint(),getPosition())/20;
        setSpeed(Math.min(calculatedSpeed,getMaxSpeed()));
        setRotation(getDirection());
    }
    private void animateJet(){
        jet.getSprite().setVisible(getSpeed()>getMaxSpeed()/20);
        jet.setScale(getSpeed()/10 + 0.7 + Math.sin(Math.toRadians(currentTimeMillis()))/4);
    }

    private void shoot(){
        if(InputManager.getInstance().getAction()&&!shooting){
            setShooting(true);
            ObjectManager.getInstance().getGame().getBulletSpawner().prepareBullet();
            ObjectManager.getInstance().getGame().getBulletSpawner().startShootingTimer();
        }
        if(!InputManager.getInstance().getAction()) {
            setShooting(false);
            ObjectManager.getInstance().getGame().getBulletSpawner().resetShootingTimer();
        }
    }

    public void setLife(int life){
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
