package com.tong;

import com.zine.zinemob.animation.AnimationListener;
import com.zine.zinemob.animation.SpriteAnimationController;
import com.zine.zinemob.drawableelement.DrawableElement;
import com.zine.zinemob.drawableelement.SpriteElement;
import com.zine.zinemob.scene.controller.Controller;
import com.zine.zinemob.scene.controller.Updateble;
import javax.microedition.lcdui.Image;

public class BallController extends Controller implements Updateble{

    private final DrawableElement ball;
    private final DrawableElement bat;
    private static final int SPEED = 4;

    private int moveX = 1;
    private int moveY = 1;

    private Image collisionImage;

    public BallController(DrawableElement ball, DrawableElement bat) {
        this.ball = ball;
        this.bat = bat;
    }

    public void init() {
        collisionImage = loadImage("/flare.png");
        ball.centerPivot();
        int middleHeight = getSceneController().getScreenElement().getHeight() / 2;
        int middleWidth = getSceneController().getScreenElement().getWidth() / 2;

        this.ball.setPosition(middleWidth, middleHeight);
    }

    public void update() {
        int xPosition = ball.getX() + moveX * SPEED;
        int yPosition = ball.getY() + moveY * SPEED;

        if (xPosition > getSceneController().getScreenElement().getWidth() || xPosition < 0) {
            moveX *= -1;
            onCollision();
        }
        if (yPosition > getSceneController().getScreenElement().getHeight() || yPosition < 0) {
            moveY *= -1;
            onCollision();
        }

        if(ball.collidesWith(bat, true)){
            moveX *= -1;
            onCollision();
        }
        
        xPosition = ball.getX() + moveX * SPEED;
        yPosition = ball.getY() + moveY * SPEED;

        ball.setPosition(xPosition, yPosition);
    }

    private void onCollision(){
        final SpriteElement flare = new SpriteElement(collisionImage, 66, 69);
        flare.centerPivot();
        flare.setPosition(ball.getX(), ball.getY());
        getSceneController().getScreenElement().addChild(flare);

        SpriteAnimationController spriteAnimation = new SpriteAnimationController(flare);
        spriteAnimation.setFrameSequence(0,4);
        spriteAnimation.setLoops(0);
        spriteAnimation.setStepsBetweenFrames(2);
        spriteAnimation.setFinishAfterExecuteAllFrames(true);
        spriteAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationFinish() {
                getSceneController().getScreenElement().removeChild(flare);
            }
        });
        getSceneController().addController(spriteAnimation);
    }

    private Image loadImage(String name) {
        try {
            return Image.createImage(name);
        } catch (Exception ex) {
            return null;
        }
    }

}
