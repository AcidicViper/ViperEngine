package me.acidviper.util.math;

import lombok.Getter;

public class Point {

    @Getter private final int x;
    @Getter private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
