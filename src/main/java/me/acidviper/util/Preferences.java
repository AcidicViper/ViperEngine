package me.acidviper.util;

import lombok.Getter;

public class Preferences {
    @Getter private final int x;
    @Getter private final int y;

    @Getter private final String title;
    @Getter private final boolean resizable;

    public Preferences(int x, int y, boolean resizable, String title) {
        this.x = x;
        this.y = y;

        this.title = title;
        this.resizable = resizable;
    }
}
