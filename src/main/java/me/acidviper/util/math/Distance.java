package me.acidviper.util.math;

public class Distance {
    public static double distance(Point a, Point b) {
        return Math.sqrt((b.getX() - a.getX())^2 + (b.getY() - a.getY())^2);
    }

    public static double distance (int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1)^2 + (y2 - y1)^2);
    }
}
