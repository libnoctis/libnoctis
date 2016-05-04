/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.

 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.ninepatch;

import org.libnoctis.util.Vector2i;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 * The Compatible Image Maker
 *
 *
 * The Compatible Image Maker can make empty images compatible for
 * the nine patch, translucent if needed. It cans also converts existing
 * ones.
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public final class CompatibleImageMaker
{
    /**
     * Create an empty nine patch compatible image with the given dimensions
     *
     * @param dimensions The dimensions of the image to create
     *
     * @return The created image
     */
    public static BufferedImage empty(Vector2i dimensions)
    {
        return empty(dimensions.getX(), dimensions.getY());
    }

    /**
     * Create an empty nine patch compatible image with the given dimensions
     *
     * @param width The width of the image to create
     * @param height The height of the image to create
     *
     * @return The created image
     */
    public static BufferedImage empty(int width, int height)
    {
        return config().createCompatibleImage(width, height);
    }

    /**
     * Create an empty translucent nine patch compatible image with the given dimensions
     *
     * @param dimensions The dimensions of the image to create
     *
     * @return The created image
     */
    public static BufferedImage translucent(Vector2i dimensions)
    {
        return translucent(dimensions.getX(), dimensions.getY());
    }

    /**
     * Create an empty translucent nine patch compatible image with the given dimensions
     *
     * @param width The width of the image to create
     * @param height The height of the image to create
     *
     * @return The created image
     */
    public static BufferedImage translucent(int width, int height)
    {
        return config().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    /**
     * Convert an existing image to a nine patch compatibl eimage
     *
     * @param image The image to convert
     *
     * @return The converted image
     */
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
