package com.acidviper;

import com.acidviper.scene.GameScene;
import me.acidviper.ViperEngine;
import me.acidviper.util.Preferences;

public class Pong {
    public static void main(String[] args) {
        // Start the engine
        new ViperEngine(new Preferences(800, 800, false, "Pong"));

        // Set the screen to be the game screen
        ViperEngine.getInstance().setCurrentScene(new GameScene());
    }

}
