package me.acidviper;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.input.ViperInput;
import me.acidviper.loader.AssetLoader;
import me.acidviper.scene.ViperScene;
import me.acidviper.util.Dimension;
import me.acidviper.util.Preferences;

public class ViperEngine {
    @Getter private static ViperEngine instance;

    @Getter @Setter private ViperScene currentScene;


    @Getter private final Dimension dimensions;
    @Getter private final Preferences prefs;
    @Getter private final String title;

    public ViperEngine(Preferences prefs) {
        instance = this;
        this.prefs = prefs;

        this.dimensions = new Dimension(prefs.getX(), prefs.getY());
        this.title = prefs.getTitle();

        init();
    }

    public void init() {
        new AssetLoader();
        new ViperInput();
    }

}
