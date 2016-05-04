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

import org.libnoctis.util.Vector2i;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The Nine Patch Chunk
 *
 *
 * The Nine Patch Chunk contains the real patch data generated
 * from the black pixels.
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisNinePatchChunk
{
    /**
     * The fixed parts of the image
     */
    private List<Rectangle> fixed;

    /**
     * The patched parts of the image
     */
    private List<Rectangle> patches;

    /**
     * The horizontal patches
     */
    private List<Rectangle> horizontalPatches = new ArrayList<Rectangle>();

    /**
     * The vertical patches
     */
    private List<Rectangle> verticalPatches   = new ArrayList<Rectangle>();

    /**
     * The horizontal padding of the chunk
     */
    private Vector2i horizontalPadding;

    /**
     * The vertical padding of the chunk
     */
    private Vector2i verticalPadding;

    /**
     * If the horizontal line starts with a patch
     */
    private boolean horizontalStartsWithPatch;

    /**
     * If the vertical line starts with a patch
     */
    private boolean verticalStartsWithPatch;

    /**
     * @return The fixed parts of the image
     */
    public List<Rectangle> getFixed()
    {
        return fixed;
    }

    /**
     * Set the fixed parts of the image
     *
     * @param fixed The rectangles that are fixed in the image
     */
    public void setFixed(List<Rectangle> fixed)
    {
        this.fixed = fixed;
    }

    /**
     * @return The patched parts of the image
     */
    public List<Rectangle> getPatches()
    {
        return patches;
    }

    /**
     * Set the patched parts of the image
     *
     * @param patches The rectangles that are patched in the image
     */
    public void setPatches(List<Rectangle> patches)
    {
        this.patches = patches;
    }

    /**
     * @return The horizontal patches as rectangles
     */
    public List<Rectangle> getHorizontalPatches()
    {
        return horizontalPatches;
    }

    /**
     * Set the horizontal patches
     *
     * @param horizontalPatches The rectangles that are the horizontal patches
     */
    public void setHorizontalPatches(List<Rectangle> horizontalPatches)
    {
        this.horizontalPatches = horizontalPatches;
    }

    /**
     * @return The vertical patches as rectangles
     */
    public List<Rectangle> getVerticalPatches()
    {
        return verticalPatches;
    }

    /**
     * Set the vertical patches
     *
     * @param verticalPatches The rectangles that are the vertical patches
     */
    public void setVerticalPatches(List<Rectangle> verticalPatches)
    {
        this.verticalPatches = verticalPatches;
    }

    /**
     * @return The horizontal padding
     */
    public Vector2i getHorizontalPadding()
    {
        return horizontalPadding;
    }

    /**
     * Set the horizontal padding
     *
     * @param horizontalPadding The new horizontal padding
     */
    public void setHorizontalPadding(Vector2i horizontalPadding)
    {
        this.horizontalPadding = horizontalPadding;
    }

    /**
     * @return The vertical padding
     */
    public Vector2i getVerticalPadding()
    {
        return verticalPadding;
    }

    /**
     * Set the vertical padding
     *
     * @param verticalPadding The new vertical padding
     */
    public void setVerticalPadding(Vector2i verticalPadding)
    {
        this.verticalPadding = verticalPadding;
    }

    /**
     * @return If the first line is a patch
     */
    public boolean isHorizontalStartsWithPatch()
    {
        return horizontalStartsWithPatch;
    }

    /**
     * Set if the first line is a patch
     *
     * @param horizontalStartsWithPatch If the first line is a patch
     */
    public void setHorizontalStartsWithPatch(boolean horizontalStartsWithPatch)
    {
        this.horizontalStartsWithPatch = horizontalStartsWithPatch;
    }

    /**
     * @return If the first column is a patch
     */
    public boolean isVerticalStartsWithPatch()
    {
        return verticalStartsWithPatch;
    }

    /**
     * Set if the first column is a patch
     *
     * @param verticalStartsWithPatch If the first columns is a patch
     */
    public void setVerticalStartsWithPatch(boolean verticalStartsWithPatch)
    {
        this.verticalStartsWithPatch = verticalStartsWithPatch;
    }
}
