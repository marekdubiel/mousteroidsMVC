package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.controller.InputManager;

import static java.lang.System.currentTimeMillis;

public class PlayerObject extends CollidableObject {
    private int life;
    private SimpleObject jet;
    private boolean shooting;
    private boolean shootingDisabled;
    private Swirler swirler;

    public PlayerObject(){
        initialize();
        initializeJet();
    }

    private void initialize(){
        super.initializeCollidableObject("spaceship", 6, Settings.getInstance().centerPoint(),90,1);
        setLife(Settings.getInstance().getLifeAmount());
        setMaxSpeed(20);
        setShooting(false);
        setShootingDisabled(false);
        GUI.getInstance().linkPlayer(this);
    }

    private void initializeJet(){
        jet = new SimpleObject(){
            PlayerObject parent = PlayerObject.this;
            double distance = 25;
            @Override
            public void update(double delta){
                super.update(delta);
                setPosition(Calculate.pointByDirectionAndDistance(parent.getPosition(),parent.getRotation()-180,distance));
                setRotation(parent.getRotation());
            }
        };
        jet.initializeGeneralObject(getPosition());
        jet.initializeImageObject("jet",6,getRotation(),1.5);
    }

    public void update(double delta){
        super.update(delta);
        if (swirler==null || !swirler.isWorking()){
            setDirectionAndSpeed();
            animateJet();
            shoot();
        }else{
            swirler.update();
        }
        checkLife();
    }

    private void setDirectionAndSpeed(){
        setDirection(Calculate.direction(InputManager.getInstance().getFocusPoint(),getPosition()));
        double calculatedSpeed = Calculate.distance(InputManager.getInstance().getFocusPoint(),getPosition())/10;
        setSpeed(Math.min(calculatedSpeed,getMaxSpeed()));
        setRotation(getDirection());
    }
    private void animateJet(){
        jet.getSprite().setVisible(getSpeed()>getMaxSpeed()/20);
        jet.setScale(getSpeed()/10 + 0.7 + Math.sin(Math.toRadians(currentTimeMillis())));
    }

    private void shoot(){
        if (InputManager.getInstance().getAction() && !shooting) {
            setShooting(true);
            ObjectManager.getInstance().getGame().getBulletSpawner().prepareBullet();
            ObjectManager.getInstance().getGame().getBulletSpawner().startShootingTimer();
        }
        if (!InputManager.getInstance().getAction()) {
            setShooting(false);
            ObjectManager.getInstance().getGame().getBulletSpawner().resetShootingTimer();
        }
    }

    public void swirl(double direction, double speed, long time){
        if (swirler !=null) {
            if(swirler.isWorking()) {
                swirler.setDirection(direction);
            }else {
                hitAndCreateSwirler(direction,speed,time);
            }
        }else {
            hitAndCreateSwirler(direction, speed, time);
        }
    }

    private void hitAndCreateSwirler(double direction, double speed, long time){
        setLife(getLife()-1);
        moveInDirection(direction,10);
        swirler = new Swirler(direction, speed, time, getRotation(), this);
        blink(time, 16, true);
        jet.getSprite().setVisible(false);
    }

    private void checkLife(){
        if (getLife()<=0) {
            ObjectManager.getInstance().getGame().setGameOver(true);
            setAlive(false);
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

    public boolean isShootingDisabled() {
        return shootingDisabled;
    }

    public void setShootingDisabled(boolean shootingDisabled) {
        this.shootingDisabled = shootingDisabled;
    }
}
