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
package org.libnoctis.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * The 2 Int Vector
 *
 * <p>
 * A Vector with two integers (x and y)
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
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

    public Vector2i()
    {
    }

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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2i other = (Vector2i) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public float length()
    {
        return (float) Math.sqrt(x * x + y * y);
    }

    @NotNull
    public static Vector2i min(@Nullable Vector2i u, @Nullable Vector2i v, @Nullable Vector2i result)
    {
        if (result == null)
        {
            result = new Vector2i();
        }
        
        if (u != null && v != null)
        {
            result.x = Math.min(u.x, v.x);
            result.y = Math.min(u.y, v.y);
        }
        else if (u == null)
        {
            result.x = v.x;
            result.y = v.y;
        }
        else if (v == null)
        {
            result.x = u.x;
            result.y = u.y;
        }

        return result;
    }
    
    @NotNull
    public static Vector2i max(@Nullable Vector2i u, @Nullable Vector2i v, @Nullable Vector2i result)
    {
        if (result == null)
        {
            result = new Vector2i();
        }
        
        if (u != null && v != null)
        {
            result.x = Math.max(u.x, v.x);
            result.y = Math.max(u.y, v.y);
        }
        else if (u == null)
        {
            result.x = v.x;
            result.y = v.y;
        }
        else if (v == null)
        {
            result.x = u.x;
            result.y = u.y;
        }

        return result;
    }
}
