package me.acidviper.resource;

import lombok.Getter;

public class Resource {
    @Getter private final ResourceType resourceType;
    @Getter private final int x;
    @Getter private final int y;

    public Resource(ResourceType e, int x, int y) {
        this.resourceType = e;

        this.x = x;
        this.y = y;
    }

    public enum ResourceType { IMAGE, TEXT, RECTANGLE, OVAL, LIGHT }
}
