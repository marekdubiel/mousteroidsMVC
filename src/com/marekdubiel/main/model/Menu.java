package com.marekdubiel.main.model;

public class Menu {
    private static Menu instance;

    public static Menu getInstance() {
        if(instance==null)
            instance = new Menu();
        return instance;
    }

    private Menu(){
    }

    public void start(){

    }
    public void update(double delta){

    }

    private void terminate(){

    }
}
