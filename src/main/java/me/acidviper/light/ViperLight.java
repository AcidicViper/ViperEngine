package me.acidviper.light;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.resource.Resource;

import java.awt.*;

public class ViperLight extends Resource {

    @Getter private final LightType type;

    @Getter @Setter private int x;
    @Getter @Setter private int y;

    @Getter private final float[] intensity;
    @Getter private final Color[] colors;

    public ViperLight(LightType type, int x, int y, float[] intensity, Color[] colors) {
        super(ResourceType.LIGHT, x, y);

        this.type = type;

        this.x = x;
        this.y = y;

        this.intensity = intensity;
        this.colors = colors;
    }

    public enum LightType { CIRCLELIGHT }
}
