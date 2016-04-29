package org.libnoctis.ninepatch;

import org.libnoctis.util.Vector2i;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public final class CompatibleImageMaker
{
    public static BufferedImage empty(Vector2i dimensions)
    {
        return empty(dimensions.getX(), dimensions.getY());
    }

    public static BufferedImage empty(int width, int height)
    {
        return config().createCompatibleImage(width, height);
    }

    public static BufferedImage translucent(Vector2i dimensions)
    {
        return translucent(dimensions.getX(), dimensions.getY());
    }

    public static BufferedImage translucent(int width, int height)
    {
        return config().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    public static BufferedImage convert(BufferedImage image)
    {
        if (image.getColorModel().equals(config().getColorModel()))
            return image;

        BufferedImage compatible = config().createCompatibleImage(
                image.getWidth(),
                image.getHeight(),
                image.getTransparency());
        Graphics g = compatible.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return compatible;
    }

    private static GraphicsConfiguration config()
    {
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                                  .getDefaultScreenDevice()
                                  .getDefaultConfiguration();
    }
}
