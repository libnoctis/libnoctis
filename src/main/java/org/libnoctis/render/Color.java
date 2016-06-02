/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
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

}
