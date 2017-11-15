package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Collidable;
import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.additional.Geometry;
import com.marekdubiel.main.controller.InputManager;

public class AsteroidObject extends CollidableObject {
    AsteroidSpawner spawner;
    int size;
    int hitPoints;

    public AsteroidObject(int size, Double2D position, double direction, AsteroidSpawner spawner){
        this.spawner = spawner;
        setSize(size);
        setHitPoints(getSize());
        super.initializeCollidableObject(shapeName(size), 3, position,direction,size);
        setSpeed(1+(ObjectManager.getInstance().getGame().getActualDifficulty()));
        setMaxSpeed(getSpeed());
    }

    private String shapeName(int size){
        switch(size){
            case 4:
                return "asteroidBig";
            case 2:
                return "asteroidMedium";
            default:
                return "asteroidSmall";
        }

    }

    @Override
    public void update(double delta){
        super.update(delta);
        wrapAround();
        rotate(delta);
        checkCollisions();
        checkHitPoints();
    }

    private void wrapAround(){
        if(getPosition().getX()>Settings.getInstance().getWindowWidth()*1.1)
            setPosition(Double2D.add(getPosition(),new Double2D(-Settings.getInstance().getWindowWidth()*1.2,0)));
        if(getPosition().getX()<Settings.getInstance().getWindowWidth()*-0.1)
            setPosition(Double2D.add(getPosition(),new Double2D(Settings.getInstance().getWindowWidth()*1.2,0)));
        if(getPosition().getY()>Settings.getInstance().getWindowHeight()*1.1)
            setPosition(Double2D.add(getPosition(),new Double2D(0,-Settings.getInstance().getWindowHeight()*1.2)));
        if(getPosition().getY()<Settings.getInstance().getWindowHeight()*-0.1)
            setPosition(Double2D.add(getPosition(),new Double2D(0,Settings.getInstance().getWindowHeight()*1.2)));
    }

    private void rotate(double delta){
        setRotation(getRotation() + (7.0-size) * delta);
    }

    private void checkCollisions(){
        for(SimpleObject objectToCheck : ObjectManager.getInstance().getObjects()) {
            if (collidesWith(objectToCheck)) {
                typeSpecificBehaviour(objectToCheck);
            }
        }
    }

    private boolean collidesWith(SimpleObject objectToCheck){
        if(!(objectToCheck instanceof Collidable))
            return false;
        if(objectToCheck == this)
            return false;
        try{
            return Geometry.checkCollision(this,(Collidable)objectToCheck);
        }catch(Exception exception){
            exception.printStackTrace();
            return false;
        }
    }

    private void typeSpecificBehaviour(SimpleObject objectToCheck){
        if(objectToCheck instanceof AsteroidObject)
            bounceOffAsteroid((AsteroidObject)objectToCheck);
        else if(objectToCheck instanceof BulletObject)
            hitByBullet((BulletObject)objectToCheck);
        else if(objectToCheck instanceof PlayerObject)
            hitPlayer((PlayerObject)objectToCheck);
    }

    private void bounceOffAsteroid(AsteroidObject asteroidHit){
        setDirection(calculateAtan2(this, asteroidHit));
        asteroidHit.setDirection(calculateAtan2(asteroidHit,this));

        uncollide(asteroidHit);
    }

    private void hitByBullet(BulletObject bulletHit){
        bulletHit.getSprite().setVisible(false);
        bulletHit.setAlive(false);

        if(getBlinker()==null || !getBlinker().isWorking()) {
            damagedBy(bulletHit);
        }
    }

    private void damagedBy(BulletObject bulletHit){
        blink(300, 6, true);
        setHitPoints(getHitPoints()-1);
        GUI.getInstance().addPoints((int)(5*ObjectManager.getInstance().getGame().getActualDifficulty()));
    }

    private void hitPlayer(PlayerObject playerHit){
        setDirection(calculateAtan2(this, playerHit));
        playerHit.swirl(calculateAtan2(playerHit, this), getSpeed()/2, 1000);
        ObjectManager.getInstance().getGame().getParticleSystem().explode(playerHit.getPosition(),8);
    }

    private void checkHitPoints(){
        if(getHitPoints()<=0){
            if (getSize() > 1){
                spawner.splitAsteroid(getSize(), getPosition(), getDirection());
                ObjectManager.getInstance().getGame().getParticleSystem().explode(getPosition(),getSize()*2);
            }else
                spawner.setAsteroidCount(spawner.getAsteroidCount()-1);
            ObjectManager.getInstance().getGame().getParticleSystem().explode(getPosition(),4);
            getSprite().setVisible(false);
            setAlive(false);
            ObjectManager.getInstance().getGame().getParticleSystem().explode(getPosition(),getSize());
            GUI.getInstance().addPoints(getSize()*(int)(5*ObjectManager.getInstance().getGame().getActualDifficulty()));
            ObjectManager.getInstance().getGame().getParticleSystem().explode(getPosition(),6);
        }
    }

    private double calculateAtan2( Collidable object, Collidable subject){
        Double2D calculatedPoint = Double2D.subtract(subject.getPosition(),object.getPosition());
        return Math.toDegrees(Math.atan2(calculatedPoint.getY(),calculatedPoint.getX()));
    }

    private void uncollide(AsteroidObject asteroidHit){
        while(collidesWith(asteroidHit)){
            moveInDirection(getDirection(),1);
            asteroidHit.moveInDirection(asteroidHit.getDirection(),1);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
