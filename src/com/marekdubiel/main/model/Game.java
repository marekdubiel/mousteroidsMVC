package com.marekdubiel.main.model;

public class Game {
    private AsteroidSpawner asteroidSpawner;
    private BulletSpawner bulletSpawner;
    private ParticleSystem particleSystem;
    private PlayerObject player;
    private boolean gameOver;
    private double actualDifficulty;

    public Game(){

    }

    public void start() {
        initializePlayer();
        initializeBulletSpawner();
        initializeAsteroidSpawner();
        initializeExplosionSpawner();
        setGameOver(false);
        setActualDifficulty(Settings.getInstance().getBaseDifficulty());
    }

    private void initializePlayer(){
        player = new PlayerObject();
    }

    private void initializeBulletSpawner(){
        bulletSpawner = new BulletSpawner(player);
    }

    private void initializeAsteroidSpawner(){
        asteroidSpawner = new AsteroidSpawner(player);
    }

    private void initializeExplosionSpawner(){
        particleSystem = new ParticleSystem();
    }

    public void update(){
        if(bulletSpawner!=null)
            bulletSpawner.shootBulletIfPrepared();
        if(asteroidSpawner!=null)
            asteroidSpawner.spawnAsteroidsIfPending();
        if(particleSystem !=null)
            particleSystem.update();
        if(isGameOver() && !particleSystem.hasParticles()){
            setGameOver(false);
            ObjectManager.getInstance().startMenu();
        }
        setActualDifficulty(Settings.getInstance().getBaseDifficulty()+(double)GUI.getInstance().getScore()/1000.0);
    }

    public BulletSpawner getBulletSpawner() {
        return bulletSpawner;
    }

    public ParticleSystem getParticleSystem() {
        return particleSystem;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getActualDifficulty() {
        return actualDifficulty;
    }

    public void setActualDifficulty(double actualDifficulty) {
        this.actualDifficulty = actualDifficulty;
    }
}
