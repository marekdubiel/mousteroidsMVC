package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.additional.Geometry;
import com.marekdubiel.main.controller.InputManager;
import com.marekdubiel.main.view.ImageSprite;
import com.marekdubiel.main.view.TextSprite;

import java.util.ArrayList;

public class ButtonObject  extends SimpleObject{
    private TextSprite additionalTextSprite;
    private boolean buttonHighlighted;
    private boolean buttonPressed;

    public ButtonObject(){

    }

    @Override
    public void initializeTextObject(String text, double size, int layer, boolean whiteFont){
        additionalTextSprite = new TextSprite(text, size, layer, this, whiteFont);
        buttonHighlighted=false;
        buttonPressed=false;
    }

    @Override
    public void update(double delta){
        super.update(delta);
        if(getReady() && additionalTextSprite!=null) {
            setRotation(getRotation() + 1.5 * delta);
            switchHighlighted();
            checkChosen();
            blinkAdditionalSprite();
        }
    }

    private void switchHighlighted(){
        if (isHighlighted()!=buttonHighlighted) {
            buttonHighlighted = isHighlighted();
            ((ImageSprite) getSprite()).reverseFillColor();
            additionalTextSprite.reverseFontColor();
        }
    }

    private boolean isHighlighted(){
        return Geometry.singleVertexIsIncluded(calculateBounds(), InputManager.getInstance().getFocusPoint());
    }

    private void blinkAdditionalSprite(){
        additionalTextSprite.setVisible(getSprite().isVisible());
    }
    private ArrayList<Double2D> calculateBounds(){
        ArrayList<Double2D> calculatedBounds;
        calculatedBounds = getSprite().getBounds();
        calculatedBounds = Geometry.scaleVertices(calculatedBounds,getScale());
        calculatedBounds = Geometry.rotateVertices(calculatedBounds,getRotation());
        calculatedBounds = Geometry.translateVertices(calculatedBounds,getPosition());

        return calculatedBounds;
    }

    private void checkChosen(){
        if(buttonHighlighted){
            if(InputManager.getInstance().getAction()) {
                buttonPressed = true;
            }
        }
    }

    public boolean getButtonPressed(){
        return buttonPressed;
    }

}
