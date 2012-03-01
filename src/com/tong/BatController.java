package com.tong;

import com.zine.zinemob.drawableelement.DrawableElement;
import com.zine.zinemob.scene.controller.Controller;
import com.zine.zinemob.scene.controller.KeyboardListener;
import javax.microedition.lcdui.game.GameCanvas;

public class BatController extends Controller implements KeyboardListener {

    private static final int SPEED = 6;

    private final DrawableElement bat;

    public BatController(DrawableElement bat) {
        this.bat = bat;
    }

    public void init() {
        super.init();
        bat.centerPivot();
        int middle = getSceneController().getScreenElement().getHeight() / 2;
        bat.setPosition(30, middle);
    }

    public void onKeyPressed(int i, int i1) {
    }

    public void onKeyRepeated(int i, int i1) {
    }

    public void onKeyReleased(int i, int i1) {
    }

    public void updateKeyStates(int keysStates) {
        if ((keysStates & GameCanvas.DOWN_PRESSED) != 0) {
            if(bat.getY() <= getSceneController().getScreenElement().getHeight()){
                bat.setPosition(bat.getX(), bat.getY() + SPEED);
            }
        }
        if ((keysStates & GameCanvas.UP_PRESSED) != 0) {
            if(bat.getY() >= 0){
                bat.setPosition(bat.getX(), bat.getY() - SPEED);
            }
        }


    }

}
