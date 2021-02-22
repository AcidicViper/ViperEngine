package me.acidviper.light.type;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.light.ViperLight;

import java.awt.*;

public class CircleLight extends ViperLight {
    @Getter @Setter private float radius;

    public CircleLight(int x, int y, float[] intensity, Color[] colors, float radius) {
        super(LightType.CIRCLELIGHT, x, y, intensity, colors);

        this.radius = radius;
    }
}
