package me.acidviper.util;

import lombok.Getter;

public class Dimension {
    @Getter private int x;
    @Getter private int y;

    public Dimension(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
