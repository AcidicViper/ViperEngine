package com.acidviper.entity;

import lombok.Getter;
import me.acidviper.input.ViperInput;
import me.acidviper.loader.AssetLoader;
import me.acidviper.util.math.Point;
import me.acidviper.util.math.Rectangle;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Paddle {
    @Getter private final int x;
    @Getter private int y;

    @Getter private final BufferedImage image;

    @Getter private final Rectangle rect;

    private final Point velocity;

    public Paddle(int x, int y) {

        // Instantiate the variables
        this.x = x;
        this.y = y;

        // Load the paddle image.
        this.image = AssetLoader.getInstance().getBufferedImage("paddle.png");

        // Instantiate the velocity vector.
        velocity = new Point(0, 0);

        rect = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void update() {
        // Check for input
        if (ViperInput.getInstance().isKeyPressed(KeyEvent.VK_W)) velocity.setY(velocity.getY() - 4);
        if (ViperInput.getInstance().isKeyPressed(KeyEvent.VK_S)) velocity.setY(velocity.getY() + 4);

        // Add the velocity to the position and reset the velocity vector
        this.y = this.y + velocity.getY();
        velocity.setY(0);

        // Update the rectangles position
        rect.setY(y);
    }
}
