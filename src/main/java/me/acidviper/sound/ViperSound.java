package me.acidviper.sound;

import lombok.Getter;

public class ViperSound {
    @Getter private static ViperSound instance;

    public ViperSound() {
        instance = this;
    }

    public void playSound(ViperWavSound s) {

    }
}
