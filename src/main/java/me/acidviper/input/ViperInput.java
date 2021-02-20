package me.acidviper.input;

import lombok.Getter;
import me.acidviper.ViperEngine;
import me.acidviper.camera.ViperCamera;
import me.acidviper.scene.ViperScene;
import me.acidviper.util.math.Point;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ViperInput implements KeyListener, MouseListener {
    @Getter private static ViperInput instance;

    private final ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
    private final ArrayList<Integer> pressedMouseButtons = new ArrayList<Integer>();

    public ViperInput() {
        instance = this;
    }

    public boolean isKeyPressed(int keyCode) {
        for (int s : pressedKeys) if (keyCode == s) return true;
        return false;
    }

    public boolean isMousePressed(int mouseCode) {
        for (int s : pressedMouseButtons) if (mouseCode == s) return true;
        return false;
    }

    public Point getMousePositionCameraRayCast() {
        ViperScene s = ViperEngine.getInstance().getCurrentScene();
        return new Point( ViperCamera.getCurrentCamera().getX() + s.getMousePosition().x, ViperCamera.getCurrentCamera().getY() + s.getMousePosition().y);
    }

    public Point getGlobalMousePosition() {
        ViperScene s = ViperEngine.getInstance().getCurrentScene();
        return new Point(s.getMousePosition().x, s.getMousePosition().y);
    }

    public void keyPressed(KeyEvent e) {
        if (pressedKeys.contains(e.getKeyCode())) return;
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Object) e.getKeyCode());
    }

    public void keyTyped(KeyEvent e) { }

    public void mouseClicked(MouseEvent e) { }

    public void mousePressed(MouseEvent e) {
        if (pressedMouseButtons.contains(e.getButton())) return;
        pressedMouseButtons.add(e.getButton());
    }

    public void mouseReleased(MouseEvent e) { pressedMouseButtons.remove((Object) e.getButton()); }

    public void mouseEntered(MouseEvent e) {  }

    public void mouseExited(MouseEvent e) {  }

}
