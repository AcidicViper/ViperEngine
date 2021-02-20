package me.acidviper.sound;

import lombok.Getter;
import me.acidviper.camera.ViperCamera;
import me.acidviper.util.math.Point;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class ViperWavSound {
    @Getter private final Clip soundClip;
    @Getter private boolean fallOfSound;
    @Getter private Point loc;
    @Getter private int fallOfDistance;
    @Getter private float volume;

    public ViperWavSound(Clip clip, Point loc, int fallOfDistance, float volume) {
        this.soundClip = clip;

        this.fallOfSound = true;
        this.loc = loc;
        this.fallOfDistance = fallOfDistance;
        this.volume = volume;
    }

    public ViperWavSound(Clip clip, float volume) {
        this.soundClip = clip;
        ((FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
    }
}
