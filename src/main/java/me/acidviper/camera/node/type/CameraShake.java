package me.acidviper.camera.node.type;

import me.acidviper.camera.node.CameraNode;

public class CameraShake implements CameraNode {

    private final double strength;
    private final double speed;
    private final double duration;

    public CameraShake(double strength, double speed, double duration) {
        this.strength = strength;
        this.speed = speed;
        this.duration = duration;
    }

    public void run() {
        // TODO: Actually do the logic here.
    }
}
