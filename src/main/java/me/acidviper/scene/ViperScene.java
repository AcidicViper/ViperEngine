package me.acidviper.scene;

import me.acidviper.ViperEngine;
import me.acidviper.camera.ViperCamera;
import me.acidviper.input.ViperInput;
import me.acidviper.light.ViperLight;
import me.acidviper.light.type.CircleLight;
import me.acidviper.resource.Resource;
import me.acidviper.resource.type.ImageResource;
import me.acidviper.resource.type.OvalResource;
import me.acidviper.resource.type.RectangleResource;
import me.acidviper.resource.type.TextResource;
import me.acidviper.util.math.Point;
import me.acidviper.util.math.Rectangle;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
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
        List<ViperLight> lightsToRender = new ArrayList<ViperLight>();

        for (Resource r : update(new ArrayList<Resource>())) {
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

        if (lightsToRender.size() != 0) {
            Graphics2D g2d = (Graphics2D) g;
            for (ViperLight light : lightsToRender) {
                if (light instanceof CircleLight)  {
                    Point loc = new Point(light.getX(), light.getY());
                    RadialGradientPaint p = new RadialGradientPaint(new Point2D.Float(loc.getX(), loc.getY()), ((CircleLight) light).getRadius(), light.getIntensity(), light.getColors());
                    g2d.setPaint(p);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .95f));

                    g2d.fillRect(0, 0, (int) ((CircleLight) light).getRadius(), (int) ((CircleLight) light).getRadius());
                    g2d.dispose();
                }
            }
        }
    }
}
