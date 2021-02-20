package me.acidviper.camera.node.type;

import me.acidviper.camera.ViperCamera;
import me.acidviper.camera.node.CameraNode;
import me.acidviper.util.math.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CameraShake implements CameraNode {

    Random r = new Random();

    private final double strength;
    private final double speed;
    private final double delay;

    public CameraShake(double strength, double speed, double delay) {
        this.strength = strength;
        this.speed = speed;
        this.delay = delay;
    }

    public void run() {
        new Timer().schedule(new TimerTask() {
            final ViperCamera c = ViperCamera.getCurrentCamera();
            final Point velocity = getRandomVelocity(new Point(0, 0));

            public void run() {
                if (c.getX() != c.getX() + velocity.getX() || c.getY() != c.getY() + velocity.getY()) {
                    if (c.getX() < c.getX() + velocity.getX()) {
                        c.setX(c.getX() + 1);
                        velocity.setX(velocity.getX() - 1);
                    } else if (c.getX() > c.getX() + velocity.getX()) {
                        c.setX(c.getX() - 1);
                        velocity.setX(velocity.getX() - 1);
                    }

                    if (c.getY() < c.getY() + velocity.getY()) {
                        c.setY(c.getY() + 1);
                        velocity.setY(velocity.getY() - 1);
                    } else if (c.getY() > c.getY() + velocity.getY()) {
                        c.setY(c.getY() - 1);
                        velocity.setY(velocity.getY() - 1);
                    }
                } else cancel();
            }
        }, (int) delay, (int) speed);

    }

    public Point getRandomVelocity(Point velocity) {
        velocity.setX(velocity.getX() + r.nextInt((int) (2 * strength)));
        velocity.setY(velocity.getY() + r.nextInt((int) (2 * strength)));
        return velocity;
    }

}
