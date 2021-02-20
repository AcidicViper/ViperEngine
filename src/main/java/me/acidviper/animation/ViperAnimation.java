package me.acidviper.animation;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class ViperAnimation {
    @Getter private final HashMap<Integer, BufferedImage> animation = new HashMap<Integer, BufferedImage>();
    @Getter @Setter private int currentFrame;

    @Getter private final int fps;
    @Getter @Setter private long lastRun;

    @Getter private final int amountOfFrames;

    public ViperAnimation(ArrayList<BufferedImage> a, int fps) {
        for (int i = 0; i < a.size(); i++) animation.put(i + 1, a.get(i));

        amountOfFrames = a.size();

        currentFrame = 1;
        this.fps = fps;

        lastRun = System.currentTimeMillis();

        ViperAnimationManager.getInstance().getAnims().add(this);
    }

    public BufferedImage currentFrame() { return animation.get(currentFrame); }

    public void reset() { currentFrame = 1; }

}
