package org.libnoctis.ninepatch;

import java.awt.image.BufferedImage;

public class ImageParser
{
    private BufferedImage image;
    private int[] pixels;
    private boolean isUpdated = false;

    public ImageParser(BufferedImage image)
    {
        this.image = image;
        this.pixels = new int[getNumberOfPixels()];
    }

    public int getNumberOfPixels()
    {
        return (image.getWidth() - 2) * (image.getHeight() - 2);
    }

    public void update()
    {
        this.isUpdated = true;
    }

    public int[] rows()
    {
        return get(1, isUpdated ? image.getHeight() - 1 : 0, image.getWidth() - 2, 1);
    }

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

    public BufferedImage getImage()
    {
        return image;
    }

    public int[] getPixels()
    {
        return pixels;
    }
}
