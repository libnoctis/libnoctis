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
package org.libnoctis.util;


/**
 * The 4 Int Vector
 *
 * <p>
 *     A Vector with four integers (x, y, w and z)
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public class Vector4i extends Vector2i
{
    /**
     * The w position
     */
    private int w;

    /**
     * The z position
     */
    private int z;

    /**
     * A 4 Integers Vector
     *
     * @param x The x position
     * @param y The y position
     * @param w The w position
     * @param z The z position
     */
    public Vector4i(int x, int y, int w, int z)
    {
        super(x, y);

        this.w = w;
        this.z = z;
    }

    /**
     * @return The w position
     */
    public int getW()
    {
        return w;
    }

    /**
     * Set the w position of this vector
     *
     * @param w The new w position
     */
    public void setW(int w)
    {
        this.w = w;
    }

    /**
     * @return The z position
     */
    public int getZ()
    {
        return z;
    }

    /**
     * Set the z position of this vector
     *
     * @param z The new z position
     */
    public void setZ(int z)
    {
        this.z = z;
    }
}
