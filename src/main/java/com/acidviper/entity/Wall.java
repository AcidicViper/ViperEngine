package com.acidviper.entity;

import lombok.Getter;
import me.acidviper.util.math.Rectangle;

public class Wall {

    @Getter private final int x;
    @Getter private final int y;

    @Getter private final int width;
    @Getter private final int height;

    @Getter private final Rectangle rect;

    public Wall(int x, int y, int width, int height) {
        // Define all the variables and then create a rect.
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        rect = new Rectangle(x, y, width, height);
    }
}
