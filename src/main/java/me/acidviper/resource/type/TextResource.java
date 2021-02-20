package me.acidviper.resource.type;

import lombok.Getter;
import me.acidviper.resource.Resource;

public class TextResource extends Resource {
    @Getter private final String text;
    @Getter private final int size;

    public TextResource(int x, int y, String text, int size) {
        super(ResourceType.TEXT, x, y);

        this.text = text;
        this.size = size;
    }
}
