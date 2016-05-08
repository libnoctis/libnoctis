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

import org.libnoctis.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

/**
 * The PatchesGroup
 *
 * <p>
 *     A group of pixels to be analyzed to check if it is
 *     a fixed pixel or patches pixel.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class PatchesGroup
{
    /**
     * The pixels to analyze
     */
    private int[] pixels;

    /**
     * The fixed pixels
     */
    private List<Vector2i> fixed = new ArrayList<Vector2i>();

    /**
     * The fixed patches
     */
    private List<Vector2i> patches = new ArrayList<Vector2i>();

    /**
     * If the pixels starts with a patch
     */
    private boolean startsWithPatch;

    /**
     * The Patches Group
     *
     * @param pixels The pixels to analyze
     */
    public PatchesGroup(int[] pixels)
    {
        this.pixels = pixels;
    }

    /**
     * Read the pixels to determine the fixed and the patched one.
     */
    public void read()
    {
        int lastIndex = 0;
        int lastPixel = pixels[0];
        boolean first = true;

        for (int i = 0; i < pixels.length; i++)
        {
            int pixel = pixels[i];
            if (pixel != lastPixel)
            {
                if (lastPixel == 0xFF000000)
                {
                    if (first)
                        startsWithPatch = true;

                    patches.add(new Vector2i(lastIndex, i));
                }
                else
                    fixed.add(new Vector2i(lastIndex, i));

                first = false;
                lastIndex = i;
                lastPixel = pixel;
            }
        }
        if (lastPixel == 0xFF000000)
        {
            if (first)
                startsWithPatch = true;

            patches.add(new Vector2i(lastIndex, pixels.length));
        }
        else
            fixed.add(new Vector2i(lastIndex, pixels.length));

        if (patches.size() == 0)
        {
            patches.add(new Vector2i(1, pixels.length));
            startsWithPatch = true;
            fixed.clear();
        }
    }

    /**
     * Set the pixels to analyze
     *
     * @param pixels The pixels to analyze
     */
    public void setPixels(int[] pixels)
    {
        this.pixels = pixels;
    }

    /**
     * @return The fixed pixels
     */
    public List<Vector2i> getFixed()
    {
        return fixed;
    }

    /**
     * @return The patches pixels
     */
    public List<Vector2i> getPatches()
    {
        return patches;
    }

    /**
     * @return If the pixels start with a patch
     */
    public boolean doesStartWithPatch()
    {
        return startsWithPatch;
    }
}
