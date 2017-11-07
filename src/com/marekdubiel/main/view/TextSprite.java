package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.SimpleObject;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class TextSprite extends Sprite {
    private String text;
    private Double size;
    private Color fontColor;
    private Font font;

    public TextSprite(String text, Double size, int layer, SimpleObject parent, boolean whiteFont){
        super(parent, layer);
        setText(text);
        this.size = size;

        if(whiteFont)
            setWhiteFont();
        else
            setBlackFont();

        setSquidFont();
        activateSprite(layer);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWhiteFont(){
        this.fontColor = Color.WHITE;
    }

    public void setBlackFont(){
        this.fontColor = Color.BLACK;
    }

    private void setSquidFont(){
        font = Font.loadFont(getClass().getClassLoader().getResource("resources/squid.ttf").toExternalForm(),size);
    }

    private void activateSprite(int layer){
        ViewManager.getInstance().addSprite(this,layer);
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

    public ArrayList<Double2D> getBounds(){
        return null;
    }
}
