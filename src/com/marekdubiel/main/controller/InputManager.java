package com.marekdubiel.main.controller;

public class InputManager {

    private static InputManager instance;

    public static InputManager getInstance() {
        if(instance==null)
            instance = new InputManager();
        return instance;
    }

    private InputManager(){

    }

    public void initializeController(){

    }
}
