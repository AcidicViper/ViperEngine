package me.acidviper.camera;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.camera.node.CameraNode;

import java.util.ArrayList;

public class ViperCamera {
    @Getter @Setter private static ViperCamera currentCamera;

    @Getter private final ArrayList<CameraNode> nodes = new ArrayList<CameraNode>();

    @Getter private final int width;
    @Getter private final int height;

    @Getter private final int x;
    @Getter private final int y;

    public ViperCamera(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;

        this.x = x;
        this.y = y;

        if (currentCamera == null) {
            currentCamera = this;
        }
    }

    public void addCameraNode(CameraNode node) {
        nodes.add(node);
    }
}
