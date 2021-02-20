package me.acidviper.util.math;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.resource.type.RectangleResource;

public class Rectangle {
    @Getter @Setter private int x;
    @Getter @Setter private int y;

    @Getter @Setter private int width;
    @Getter @Setter private int height;

    public Rectangle(RectangleResource e) {
        this.x = e.getX();
        this.y = e.getY();
        this.width =  e.getWidth();
        this.height = e.getHeight();
    }

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rectangle rec) {
        if (x >= rec.getX() + rec.getWidth() || rec.getX() >= x + width) return false;
        return y > rec.getY() + rec.getHeight() && rec.getY() > y + height;
    }

    public boolean intersects(int x, int y, int width, int height) {
        Rectangle rec = new Rectangle(x, y, width, height);

        if (x >= rec.getX() + rec.getWidth() || rec.getX() >= x + width) return false;
        return y > rec.getY() + rec.getHeight() && rec.getY() > y + height;
    }
}
