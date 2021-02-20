package me.acidviper.loader;

import lombok.Getter;
import me.acidviper.ViperEngine;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetLoader {
    @Getter private static AssetLoader instance;

    public AssetLoader() {
        instance = this;
    }

    public BufferedImage getBufferedImage(String location) {
        if (!location.startsWith("/")) location = "/" + location;
        BufferedImage image = null;
        try { image = ImageIO.read(ViperEngine.getInstance().getClass().getResource(location));
        } catch (Exception e) { e.printStackTrace(); }

        if (image == null) {
            System.out.println("ViperEngine: The given string location returns a null image. Error Code : 2");
        }
        return image;
    }

    public Clip getWavSound(String location) {
        Clip clip = null;
        try {
            if (!location.startsWith("/")) location = "/" + location;
            AudioInputStream input = AudioSystem.getAudioInputStream(ViperEngine.getInstance().getClass().getResource(location));
            clip = AudioSystem.getClip();
            clip.open(input);
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("ViperEngine: The given string location returns a null sound. Error Code : 3");
        }

        return clip;
    }
}
