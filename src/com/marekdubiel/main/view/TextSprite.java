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
        super(parent);
        setText(text);
        this.size = size;

        if(whiteFont)
            setWhiteFont();
        else
            setBlackFont();

        setSquidFont();
        activateSprite(layer);
    }

    private void setText(String text) {
        this.text = text;
    }

    private void setWhiteFont(){
        this.fontColor = Color.WHITE;
    }

    private void setBlackFont(){
        this.fontColor = Color.BLACK;
    }

    public void reverseFontColor(){
        this.fontColor = Color.rgb(255-(int)(fontColor.getRed()*255),255-(int)(fontColor.getGreen()*255),255-((int)fontColor.getBlue()*255));
    }

    private void setSquidFont(){
        font = Font.loadFont(getClass().getClassLoader().getResource("resources/squid.ttf").toExternalForm(),size);
    }

    private void activateSprite(int layer){
        ViewManager.getInstance().addDrawable(this,layer);
        super.update();

    }

    public void render(GraphicsContext graphicsContext){
        super.update();
        if(isVisible()) {
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.setTextBaseline(VPos.CENTER);
            graphicsContext.setFill(fontColor);
            graphicsContext.setFont(font);
            graphicsContext.fillText(text, getPosition().getX(), getPosition().getY());
        }
    }

    public ArrayList<Double2D> getBounds(){
        return null;
    }
}
