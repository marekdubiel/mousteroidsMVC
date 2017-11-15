package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Double2D;

import static java.lang.System.currentTimeMillis;

public class BulletSpawner {
    private PlayerObject shootingObject;
    private boolean bulletPrepared;
    private long shootingStartTime;
    private long delay;
    private double spawnDistance;

    public BulletSpawner(PlayerObject shootingObject){
        this.shootingObject = shootingObject;
        setBulletPrepared(false);
        delay = 175;
        spawnDistance = 15;
    }

    public void prepareBullet(){
        setBulletPrepared(true);
    }

    public void shootBulletIfPrepared(){
        if(bulletPrepared) {
            shoot();
            setBulletPrepared(false);
        }
        if(shootingStartTime!=0){
            if(shootingStartTime<currentTimeMillis()-delay){
                shoot();
                startShootingTimer();
            }
        }
    }

    private void shoot(){
        if(shootingObject!=null && shootingObject.isAlive() && !shootingObject.isShootingDisabled()) {
            Double2D bulletPosition = Calculate.pointByDirectionAndDistance(shootingObject.getPosition(), shootingObject.getRotation(), spawnDistance);
            new BulletObject(bulletPosition, shootingObject.getRotation());
        }
    }

    public void startShootingTimer(){
        shootingStartTime = currentTimeMillis();
    }

    public void resetShootingTimer(){
        shootingStartTime = 0;
    }

    public boolean isBulletPrepared() {
        return bulletPrepared;
    }

    public void setBulletPrepared(boolean bulletPrepared) {
        this.bulletPrepared = bulletPrepared;
    }
}
