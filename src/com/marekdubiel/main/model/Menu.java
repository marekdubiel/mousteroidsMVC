package com.marekdubiel.main.model;

public class Menu {
    private static Menu instance;
    private PlayerObject player;

    public static Menu getInstance() {
        if(instance==null)
            instance = new Menu();
        return instance;
    }

    private Menu(){
    }

    public void start(){
        player = new PlayerObject();
    }
    public void update(double delta){

    }

    private void terminate(){

    }
}
