package com.acidviper.entity;

import com.acidviper.scene.GameScene;
import lombok.Getter;
import me.acidviper.ViperEngine;
import me.acidviper.loader.AssetLoader;
import me.acidviper.util.math.Point;
import me.acidviper.util.math.Rectangle;

import java.awt.image.BufferedImage;

public class Ball {

    @Getter private final Rectangle rect;

    @Getter private int x;
    @Getter private int y;

    @Getter private final BufferedImage image;

    private final Point velocity;

    // The speed which the ball will travel, hard coded.
    private final int speed = 2;

    public Ball() {
        // Define the balls starting position to the middle of the screen.
        this.x = 800 / 2;
        this.y = 800 / 2;

        // Load the ping pong ball image
        this.image = AssetLoader.getInstance().getBufferedImage("PingPongBall.png");

        // Define the rect that will be used for collisions.
        this.rect = new Rectangle(x, y, image.getWidth(), image.getHeight());

        velocity = new Point(4, 4);
    }

    public void update() {
        //Check if the ball has collided with a paddle or the walls.
        if (rect.intersects(GameScene.getInstance().getTopWall().getRect()) || rect.intersects(GameScene.getInstance().getBottomWall().getRect())) velocity.setY(-velocity.getY());
        if (rect.intersects(GameScene.getInstance().getPaddleOne().getRect()) || rect.intersects(GameScene.getInstance().getPaddleTwo().getRect())) velocity.setX(-velocity.getX());

        // Change the position based ont he velocity
        this.x = x + velocity.getX();
        this.y = y + velocity.getY();

        // Update the rectangles position
        this.rect.setX(x);
        this.rect.setY(y);
    }
}
