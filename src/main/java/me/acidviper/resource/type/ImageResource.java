package me.acidviper.resource.type;

import lombok.Getter;
import me.acidviper.resource.Resource;

import java.awt.image.BufferedImage;

public class ImageResource extends Resource {
    @Getter private final BufferedImage image;

    public ImageResource(BufferedImage image, int x, int y) {
        super(ResourceType.IMAGE, x, y);

        this.image = image;
    }

}
