package com.acidviper.scene;

import com.acidviper.entity.Ball;
import com.acidviper.entity.Paddle;
import com.acidviper.entity.Wall;
import lombok.Getter;
import me.acidviper.camera.ViperCamera;
import me.acidviper.loader.AssetLoader;
import me.acidviper.resource.Resource;
import me.acidviper.resource.type.ImageResource;
import me.acidviper.resource.type.TextResource;
import me.acidviper.scene.ViperScene;
import java.util.ArrayList;

public class GameScene extends ViperScene {
    @Getter private static GameScene instance;

    private final Ball ball;

    @Getter private final Paddle paddleOne;
    @Getter private final Paddle paddleTwo;

    @Getter private final Wall topWall;
    @Getter private final Wall bottomWall;

    private final ImageResource backgroundImage;

    public GameScene() {
        instance = this;

        // Instantiate Camera
        new ViperCamera(800, 800, 0, 0);

        // Instantiate the ball and paddles
        ball = new Ball();

        paddleOne = new Paddle(40, getWidth() / 2);
        paddleTwo = new Paddle(getWidth() - 40, getWidth() / 2);

        // Load the background image.
        backgroundImage = new ImageResource(AssetLoader.getInstance().getBufferedImage("Background.png"), 0, 0);

        // Define the walls
        topWall = new Wall(0, -800, getWidth(), getHeight());
        bottomWall = new Wall(0, 800, getWidth(), getHeight());

        setRunning(true);
    }

    // Fixed update
    public void update() {
        // Calling the update method on the ball and paddles, this deals with collision, and movement
        ball.update();

        paddleOne.update();
        paddleTwo.update();
    }

    // Render, anything you want to draw you do here.
    public ArrayList<Resource> render(ArrayList<Resource> toDraw) {
        // Draw The Background
        toDraw.add(backgroundImage);

        // Draw the ping pong ball
        toDraw.add(new ImageResource(ball.getImage(), ball.getX(), ball.getY()));

        // Draw the paddles..

        toDraw.add(new ImageResource(paddleOne.getImage(), paddleOne.getX(), paddleOne.getY()));
        toDraw.add(new ImageResource(paddleTwo.getImage(), paddleTwo.getX(), paddleTwo.getY()));

        toDraw.add(new TextResource(15, 700, "FPS: " + getFps(), 35));

        return toDraw;
    }
}
