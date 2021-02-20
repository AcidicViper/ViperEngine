package me.acidviper.animation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ViperAnimationManager {
    @Getter private static ViperAnimationManager instance;
    @Getter private final ArrayList<ViperAnimation> anims = new ArrayList<ViperAnimation>();

    public ViperAnimationManager() {
        instance = this;

        new Timer().schedule(new TimerTask() {
            public void run() {
                for (ViperAnimation a : anims) {
                    int fps = a.getFps();
                    long lastRan = a.getLastRun();

                    if (System.currentTimeMillis() >= lastRan + (1000 / fps)) {
                        if (a.getCurrentFrame() + 1 > a.getAmountOfFrames()) a.setCurrentFrame(0);
                        a.setCurrentFrame(a.getCurrentFrame() + 1);
                        a.setLastRun(System.currentTimeMillis());
                    }
                }
            }
        }, 0, 5);
    }
}
