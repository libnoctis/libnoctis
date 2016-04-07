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
package org.libnoctis.render;


public class Color
{
	private static final int MASK = 0xff;

	public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 1.0f);
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

	public Color(float red, float green, float blue, float alpha)
	{
		this((byte) (red * 255.0f), (byte) (green * 255.0f), (byte) (blue * 255.0f), (byte) (alpha * 255.0f));
	}

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

	public float getRedFloat()
	{
		return (red & MASK) / 255.0f;
	}

	public float getGreenFloat()
	{
		return (green & MASK) / 255.0f;
	}

	public float getBlueFloat()
	{
		return (blue & MASK) / 255.0f;
	}

	public float getAlphaFloat()
	{
		return (alpha & MASK) / 255.0f;
	}
}
