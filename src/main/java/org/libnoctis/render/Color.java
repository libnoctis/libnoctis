/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Libnoctis is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.render;

/**
 * A color
 *
 * @author Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public class Color
{
    /**
     * The color mask (to signed and unsigned byte)
     */
    private static final int MASK = 0xff;

    /**
     * The red Color
     */
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 1.0f);

    /**
     * The green color
     */
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);

    /**
     * The blue color
     */
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);

    /**
     * The white color
     */
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    /**
     * Red component of this color (0-255).
     */
    private final byte red;

    /**
     * Green component of this color (0-255).
     */
    private final byte green;

    /**
     * Blue component of this color (0-255).
     */
    private final byte blue;

    /**
     * Alpha (transparency) component of this color (0-255).
     */
    private final byte alpha;

    /**
     * A color (opaque)
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     */
    public Color(float red, float green, float blue)
    {
        this(red, green, blue, 1.0f);
    }

    public Color(int argb)
    {
        this((argb >> 16) & MASK, (argb >> 8) & MASK, (argb) & MASK, (argb >> 24) & MASK);
    }

    public Color(int r, int g, int b)
    {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a)
    {
        this((byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
     * A color
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     * @param alpha The transparency of the color
     */
    public Color(float red, float green, float blue, float alpha)
    {
        this((byte) (red * 255.0f), (byte) (green * 255.0f), (byte) (blue * 255.0f), (byte) (alpha * 255.0f));
    }

    /**
     * A color (opaque)
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     */
    public Color(byte red, byte green, byte blue)
    {
        this(red, green, blue, Byte.MAX_VALUE);
    }

    /**
     * A color
     *
     * @param red The red of the color
     * @param green The green of the color
     * @param blue The blue of the color
     * @param alpha The transparency of the color
     */
    public Color(byte red, byte green, byte blue, byte alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * Gets the red.
     *
     * @return the red.
     */
    public byte getRed()
    {
        return (byte) (red & MASK);
    }

    /**
     * Gets the green.
     *
     * @return the green.
     */
    public byte getGreen()
    {
        return (byte) (green & MASK);
    }

    /**
     * Gets the blue.
     *
     * @return the blue.
     */
    public byte getBlue()
    {
        return (byte) (blue & MASK);
    }

    /**
     * Gets the alpha.
     *
     * @return the alpha.
     */
    public byte getAlpha()
    {
        return (byte) (alpha & MASK);
    }

    /**
     * Gets the red as a float
     *
     * @return The red
     */
    public float getRedFloat()
    {
        return (red & MASK) / 255.0f;
    }

    /**
     * Gets the green as a float
     *
     * @return The green
     */
    public float getGreenFloat()
    {
        return (green & MASK) / 255.0f;
    }

    /**
     * Gets the blue as a float
     *
     * @return The blue
     */
    public float getBlueFloat()
    {
        return (blue & MASK) / 255.0f;
    }

    /**
     * Gets the alpha as a float
     *
     * @return The alpha
     */
    public float getAlphaFloat()
    {
        return (alpha & MASK) / 255.0f;
    }

    @Override
    public String toString()
    {
        return "Color [red=" + red + ", green=" + green + ", blue=" + blue + ", alpha=" + alpha + ", getRed()=" + getRed() + ", getGreen()=" + getGreen() + ", getBlue()=" + getBlue() + ", getAlpha()=" + getAlpha() + ", getRedFloat()=" + getRedFloat() + ", getGreenFloat()=" + getGreenFloat() + ", getBlueFloat()=" + getBlueFloat() + ", getAlphaFloat()=" + getAlphaFloat() + "]";
    }

    private static final Color[] MINECRAFT_CODES = {new Color(0xff000000), new Color(0xff0000aa), new Color(0xff00aa00), new Color(0xff00aaaa), new Color(0xffaa0000), new Color(0xffaa00aa), new Color(0xffffaa00), new Color(0xffaaaaaa), new Color(0xff555555), new Color(0xff5555ff), new Color(0xff55ff55), new Color(0xff55ffff), new Color(0xffff5555), new Color(0xffff55ff), new Color(0xffffff55), new Color(0xffffffff), new Color(0xff000000), new Color(0xff00002a), new Color(0xff002a00), new Color(0xff002a2a), new Color(0xff2a0000), new Color(0xff2a002a), new Color(0xff2a2a00), new Color(0xff2a2a2a), new Color(0xff151515), new Color(0xff15153f), new Color(0xff153f15), new Color(0xff153f3f), new Color(0xff3f1515), new Color(0xff3f153f), new Color(0xff3f3f15), new Color(0xff3f3f3f), new Color(0xff000000), new Color(0xff0000aa), new Color(0xff00aa00), new Color(0xff00aaaa), new Color(0xffaa0000), new Color(0xffaa00aa), new Color(0xffffaa00), new Color(0xffaaaaaa), new Color(0xff555555), new Color(0xff5555ff), new Color(0xff55ff55), new Color(0xff55ffff), new Color(0xffff5555), new Color(0xffff55ff), new Color(0xffffff55), new Color(0xffffffff), new Color(0xff000000), new Color(0xff00002a), new Color(0xff002a00), new Color(0xff002a2a), new Color(0xff2a0000), new Color(0xff2a002a), new Color(0xff2a2a00), new Color(0xff2a2a2a), new Color(0xff151515), new Color(0xff15153f), new Color(0xff153f15), new Color(0xff153f3f), new Color(0xff3f1515), new Color(0xff3f153f), new Color(0xff3f3f15), new Color(0xff3f3f3f)};

    public static Color valueOf(String str)
    {
        if (str == null || str.length() == 0)
        {
            return null;
        }
        else if (str.length() == 1)
        {
            int index = "0123456789abcdefklmnor".indexOf(str.toLowerCase());
            return MINECRAFT_CODES[index];
        }
        else
        {
            try
            {
                int color = (int) Long.parseLong(str, 16);
                return new Color(color);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }

}
