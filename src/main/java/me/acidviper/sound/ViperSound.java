package me.acidviper.sound;

import lombok.Getter;
import me.acidviper.util.math.Distance;
import me.acidviper.util.math.Point;

import javax.sound.sampled.FloatControl;

public class ViperSound {
    @Getter private static ViperSound instance;

    public ViperSound() {
        instance = this;
    }

    public void playSound(ViperWavSound s, Point p, int maxDistance) {
        if (!s.isFallOfSound())  {
            playSound(s);
            return;
        }

        if (Distance.distance(p, s.getLoc()) < s.getFallOfDistance()) {
            playSound(s);
            return;
        }

        double distance = Distance.distance(p, s.getLoc());
        double passedLimit = distance - s.getFallOfDistance();

        if (passedLimit > maxDistance) return;

        double precentage = (passedLimit/maxDistance) / 100;

        ((FloatControl) s.getSoundClip().getControl(FloatControl.Type.MASTER_GAIN)).setValue((float) ((precentage / 100) * s.getVolume()));
    }

    public void playSound(ViperWavSound s) {
        s.getSoundClip().start();
    }
}
