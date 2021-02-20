package me.acidviper.resource.type;

import lombok.Getter;
import me.acidviper.resource.Resource;

public class OvalResource extends Resource {

    @Getter private final int width;
    @Getter private final int height;

    public OvalResource(ResourceType e, int x, int y, int width, int height) {
        super(ResourceType.OVAL, x, y);

        this.width = width;
        this.height = height;
    }
}
