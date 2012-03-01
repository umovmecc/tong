package com.tong;

import com.zine.zinemob.drawableelement.SpriteElement;
import com.zine.zinemob.scene.Scene;
import javax.microedition.lcdui.Image;

public class TongScene extends Scene {

    public void init() {
        setClearColor(0xff5080ff);
        SpriteElement bat = new SpriteElement(loadImage("/bat.png"));
        SpriteElement ball = new SpriteElement(loadImage("/ball.png"));
        getScreenElement().addChild(bat);
        getScreenElement().addChild(ball);
        addController(new BatController(bat));
        addController(new BallController(ball, bat));
    }

    private Image loadImage(String name) {
        try {
            return Image.createImage(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
