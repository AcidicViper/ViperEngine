package me.acidviper.sound;

import lombok.Getter;
import me.acidviper.camera.ViperCamera;
import me.acidviper.util.math.Point;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class ViperWavSound {
    @Getter private Clip soundClip;

    public ViperWavSound(Clip clip, Point loc, int fallOfDistance) {
        this.soundClip = clip;
        ((FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue();
    }

    public ViperWavSound(Clip clip, float volume) {
        this.soundClip = clip;
        ((FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(volume);
    }
}
