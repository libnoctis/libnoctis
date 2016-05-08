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
package org.libnoctis.ninepatch;

/**
 * The Drawing Data
 *
 * <p>
 *     Temporary data used to generate the nine patch
 *     chunk. Not documented, just temp data.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class DrawingData
{
    private int horizontalRemainder;
    private int verticalRemainder;
    private float horizontalPatchesSum;
    private float verticalPatchesSum;

    public DrawingData()
    {
    }

    public DrawingData(int horizontalRemainder, int verticalRemainder, float horizontalPatchesSum, float verticalPatchesSum)
    {
        this.horizontalRemainder = horizontalRemainder;
        this.verticalRemainder = verticalRemainder;
        this.horizontalPatchesSum = horizontalPatchesSum;
        this.verticalPatchesSum = verticalPatchesSum;
    }

    public int getHorizontalRemainder()
    {
        return horizontalRemainder;
    }

    void setHorizontalRemainder(int horizontalRemainder)
    {
        this.horizontalRemainder = horizontalRemainder;
    }

    public int getVerticalRemainder()
    {
        return verticalRemainder;
    }

    void setVerticalRemainder(int verticalRemainder)
    {
        this.verticalRemainder = verticalRemainder;
    }

    public float getHorizontalPatchesSum()
    {
        return horizontalPatchesSum;
    }

    void setHorizontalPatchesSum(float horizontalPatchesSum)
    {
        this.horizontalPatchesSum = horizontalPatchesSum;
    }

    public float getVerticalPatchesSum()
    {
        return verticalPatchesSum;
    }

    void setVerticalPatchesSum(float verticalPatchesSum)
    {
        this.verticalPatchesSum = verticalPatchesSum;
    }
}