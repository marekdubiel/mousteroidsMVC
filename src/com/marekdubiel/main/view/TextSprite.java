package com.marekdubiel.main.view;

import com.marekdubiel.main.model.GameObject;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TextSprite extends Sprite {
    private String text;
    private Double size;
    private Color fontColor;
    private Font font;

    public TextSprite(String text, Double size, GameObject parent){
        super(parent);
        this.text = text;
        this.size = size;

        setWhiteSquidFont();
        activateSprite();
    }

    private void setWhiteSquidFont(){
        fontColor = Color.WHITE;
        font = Font.loadFont("resources/squid.ttf",size);
    }

    private void activateSprite(){
        ViewManager.getInstance().addSprite(this);
        super.update();

    }

    public void render(GraphicsContext graphicsContext){
        super.update();
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFill(fontColor);
        graphicsContext.setFont(font);
        graphicsContext.fillText(text,position.getX(),position.getY());

    }
}
