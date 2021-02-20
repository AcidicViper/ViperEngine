package me.acidviper.scene;

import me.acidviper.ViperEngine;
import me.acidviper.camera.ViperCamera;
import me.acidviper.input.ViperInput;
import me.acidviper.resource.Resource;
import me.acidviper.resource.type.ImageResource;
import me.acidviper.util.math.Rectangle;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ViperScene extends Frame {
    private Image doubleBuffer;

    public ViperScene() {
        if (ViperEngine.getInstance() == null) {
            System.out.println("ViperEngine: ViperEngine instance is null, try instantiating a ViperEngine before extending ViperScene! Error Code : 1");
            return;
        }

        setTitle(ViperEngine.getInstance().getTitle());
        setSize(ViperEngine.getInstance().getDimensions().getX(), ViperEngine.getInstance().getDimensions().getY());
        setResizable(ViperEngine.getInstance().getPrefs().isResizable());

        addMouseListener(ViperInput.getInstance());
        addKeyListener(ViperInput.getInstance());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        /* Temporary way of calling update, will have to make a way that actually isn't set and involves FPS,
        this could run before previous run calls finish */
        new Timer().schedule(new TimerTask() {
            public void run() {
                repaint();
            }
        }, 0, 17);

        setVisible(true);
    }

    public abstract ArrayList<Resource> update(ArrayList<Resource> drawResources);

    public void update(Graphics g) {
        Dimension size = getSize();
        if (doubleBuffer == null || doubleBuffer.getWidth(this) != size.width || doubleBuffer.getHeight(this) != size.height)
            doubleBuffer = createImage(size.width, size.height);
        if (doubleBuffer != null) {
            Graphics g2 = doubleBuffer.getGraphics();
            paint(g2);
            g2.dispose();
            g.drawImage(doubleBuffer, 0, 0, null);
        } else paint(g);
    }

    public void paint(Graphics g) {
        for (Resource r : update(new ArrayList<Resource>())) {
            if (r == null) break;
            Rectangle rec = new Rectangle(ViperCamera.getCurrentCamera().getX(), ViperCamera.getCurrentCamera().getY(), ViperCamera.getCurrentCamera().getWidth(), ViperCamera.getCurrentCamera().getHeight());
            if (r.getResourceType() == Resource.ResourceType.IMAGE) {
                ImageResource s = (ImageResource) r;
                if(s.getX() + s.getImage().getWidth() >= rec.getX() && rec.getX() + rec.getWidth() >= s.getX() - s.getImage().getWidth() && s.getY() + s.getImage().getHeight() >= rec.getY() && rec.getY() + rec.getHeight() >= s.getY()) {
                    g.drawImage(s.getImage(), s.getX(), s.getY(), null);
                }
            }
        }
    }
}
