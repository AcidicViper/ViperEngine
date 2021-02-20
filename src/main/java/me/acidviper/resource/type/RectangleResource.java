package me.acidviper.resource.type;

import lombok.Getter;
import me.acidviper.resource.Resource;
import me.acidviper.util.math.Rectangle;

public class RectangleResource extends Resource {

    @Getter private final int width;
    @Getter private final int height;

    public RectangleResource(int x, int y, int width, int height) {
        super(ResourceType.RECTANGLE, x, y);

        this.width = width;
        this.height = height;
    }

    public Rectangle toRectangle() {
        return new Rectangle(this);
    }
}
