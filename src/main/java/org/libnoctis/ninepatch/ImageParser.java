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

import java.awt.image.BufferedImage;

/**
 * The ImageParser
 *
 * Parse an image to get its pixels, from the rows
 * or the columns.
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ImageParser
{
    /**
     * The image to parse
     */
    private BufferedImage image;

    /**
     * An array of the image pixels withtout the black nine patch pixels
     */
    private int[] pixels;

    /**
     * If it was updated for the next part of the chunk generation
     * If true, the parser will read the nine patch black pixels
     */
    private boolean isUpdated = false;

    /**
     * The Image Parser
     *
     * @param image The image to parse
     */
    public ImageParser(BufferedImage image)
    {
        this.image = image;
        this.pixels = new int[getNumberOfPixels()];
    }

    /**
     * @return The number of pixels of the image without the nine patch black pixels
     */
    public int getNumberOfPixels()
    {
        return (image.getWidth() - 2) * (image.getHeight() - 2);
    }

    /**
     * Update the parser to get the black pixels when parsing
     */
    public void update()
    {
        this.isUpdated = true;
    }

    /**
     * @return The image rows
     */
    public int[] rows()
    {
        return get(1, isUpdated ? image.getHeight() - 1 : 0, image.getWidth() - 2, 1);
    }

    /**
     * @return The image columns
     */
    public int[] columns()
    {
        return get(isUpdated ? image.getWidth() - 1 : 0, 1, 1, image.getHeight() - 2);
    }

    private int[] get(int x, int y, int w, int z)
    {
        int imageType = this.getImage().getType();

        if (imageType == BufferedImage.TYPE_INT_ARGB || imageType == BufferedImage.TYPE_INT_RGB)
            return (int[]) this.getImage().getRaster().getDataElements(x, y, w, z, this.getPixels());

        return this.getImage().getRGB(x, y, w, z, this.getPixels(), 0, w);
    }

    /**
     * @return The given image to parse
     */
    public BufferedImage getImage()
    {
        return image;
    }

    /**
     * @return An array of pixels with the size of the image without the nine patch black pixels
     */
    public int[] getPixels()
    {
        return pixels;
    }
}
