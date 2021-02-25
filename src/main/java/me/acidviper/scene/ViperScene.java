package me.acidviper.scene;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.ViperEngine;
import me.acidviper.camera.ViperCamera;
import me.acidviper.input.ViperInput;
import me.acidviper.light.ViperLight;
import me.acidviper.resource.Resource;
import me.acidviper.resource.type.ImageResource;
import me.acidviper.resource.type.OvalResource;
import me.acidviper.resource.type.RectangleResource;
import me.acidviper.resource.type.TextResource;
import me.acidviper.util.math.Rectangle;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class ViperScene extends Frame {

    @Getter private boolean running = false;
    @Getter private int fps;

    private Image doubleBuffer;

    private long tick;
    private int tempFPS;

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

        setVisible(true);
    }

    public void setRunning(boolean run) {
        if (running) {
            running = false;
            return;
        }

        running = true;
        init();
    }

    public void init() {
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        long nextFPSUpdate = System.currentTimeMillis() + 1000;
        double tickRate = 1.0d / 60.0d;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator > tickRate) {
                update();
                tick++;
                accumulator -= tickRate;
            }

            repaint();
            tempFPS++;

            if (System.currentTimeMillis() > nextFPSUpdate) {
                System.out.println("FPS: " + tempFPS + " Ticks: " + tick);
                fps = tempFPS;
                tempFPS = 0;
                tick = 0;
                nextFPSUpdate = System.currentTimeMillis() + 1000;
            }
        }
    }

    public abstract void update();
    public abstract ArrayList<Resource> render(ArrayList<Resource> toDraw);

    public void update(Graphics g) {
        Dimension size = getSize();
        if (doubleBuffer == null ||
                doubleBuffer.getWidth(this) != size.width ||
                doubleBuffer.getHeight(this) != size.height)
        {
            doubleBuffer = createImage(size.width, size.height);
        }
        if (doubleBuffer != null) {
            // paint to double buffer
            Graphics g2 = doubleBuffer.getGraphics();
            paint(g2);
            g2.dispose();
            // copy double buffer to screen
            g.drawImage(doubleBuffer, 0, 0, null);
        }
        else {
            // couldn't create double buffer, just paint to screen
            paint(g);
        }
    }

    public void paint(Graphics g) {
        if (!running) return;

        List<ViperLight> lightsToRender = new ArrayList<ViperLight>();
        for (Resource r : render(new ArrayList<Resource>())) {
            if (r == null) break;
            Rectangle rec = new Rectangle(ViperCamera.getCurrentCamera().getX(), ViperCamera.getCurrentCamera().getY(), ViperCamera.getCurrentCamera().getWidth(), ViperCamera.getCurrentCamera().getHeight());

            switch (r.getResourceType()) {
                case IMAGE:
                    ImageResource a = (ImageResource) r;
                    if(a.getX() + a.getImage().getWidth() >= rec.getX() && rec.getX() + rec.getWidth() >= a.getX() - a.getImage().getWidth() && a.getY() + a.getImage().getHeight() >= rec.getY() && rec.getY() + rec.getHeight() >= a.getY())
                        g.drawImage(a.getImage(), a.getX(), a.getY(), null);
                    break;
                case TEXT:
                    TextResource b = (TextResource) r;
                    g.setFont(g.getFont().deriveFont((float) b.getSize()));
                    g.drawString(b.getText(), b.getX(), b.getY());
                    break;
                case RECTANGLE:
                    RectangleResource c = (RectangleResource) r;
                    if(c.getX() + c.getWidth() >= rec.getX() && rec.getX() + rec.getWidth() >= c.getX() - c.getWidth() && c.getY() + c.getHeight() >= rec.getY() && rec.getY() + rec.getHeight() >= c.getY())
                        g.drawRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
                    break;
                case OVAL:
                    OvalResource d = (OvalResource) r;
                    if(d.getX() + d.getWidth() >= rec.getX() && rec.getX() + rec.getWidth() >= d.getX() - d.getWidth() && d.getY() + d.getHeight() >= rec.getY() && rec.getY() + rec.getHeight() >= d.getY())
                        g.drawOval(d.getX(), d.getY(), d.getWidth(), d.getHeight());
                    break;
                case LIGHT:
                    lightsToRender.add((ViperLight) r);
                    break;
            }
        }

        // TODO: Make the light system actually function
        if (lightsToRender.size() != 0) {

        }
    }
}
