package org.libnoctis.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class Dimension
{
    public int width;
    public int height;

    public Dimension()
    {
        super();
    }

    public Dimension(int w, int h)
    {
        this.width = w;
        this.height = h;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @return {@code true} if the given {@link Dimension} fits in this
     *         {@link Dimension}.
     */
    public boolean fit(Dimension dim)
    {
        return dim.width <= width && dim.height <= height;
    }
    
    /**
     * @return {@code true} if the given {@link Dimension} fits in this
     *         {@link Dimension}.
     */
    public boolean fit(int width, int height)
    {
        return width <= this.width && height <= this.height;
    }

    @NotNull
    public static Dimension min(@Nullable Dimension u, @Nullable Dimension v, @Nullable Dimension result)
    {
        if (result == null)
        {
            result = new Dimension();
        }

        if (u != null && v != null)
        {
            result.width = Math.min(u.width, v.width);
            result.height = Math.min(u.height, v.height);
        }
        else if (u == null)
        {
            result.width = v.width;
            result.height = v.height;
        }
        else if (v == null)
        {
            result.width = u.width;
            result.height = u.height;
        }

        return result;
    }
}
