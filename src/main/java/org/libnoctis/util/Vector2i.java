/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 * This file is part of Libnoctis.
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.util;


/**
 * The 2 Int Vector
 * <p>
 * A Vector with two integers (x and y)
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class Vector2i
{
    /**
     * The x position
     */
    private int x;

    /**
     * The y position
     */
    private int y;

    /**
     * A 2 Integers Vector
     *
     * @param x The x position
     * @param y The y position
     */
    public Vector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x position
     */
    public int getX()
    {
        return x;
    }

    /**
     * Set the x position of this vector
     *
     * @param x The new x position
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * @return The y position
     */
    public int getY()
    {
        return y;
    }

    /**
     * Set the y position of this vector
     *
     * @param y The new y position
     */
    public void setY(int y)
    {
        this.y = y;
    }

    public Vector2i add(int x, int y)
    {
        this.x += x;
        this.y += y;

        return this;
    }

    @Override
    public String toString()
    {
        return "Vector2i [x=" + x + ", y=" + y + "]";
    }

    public float length()
    {
        return (float) Math.sqrt(x * x + y * y);
    }
}
