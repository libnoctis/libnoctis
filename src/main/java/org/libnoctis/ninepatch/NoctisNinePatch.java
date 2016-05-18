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
package org.libnoctis.ninepatch;

import com.android.ninepatch.GraphicsUtilities;
import com.android.ninepatch.NinePatch;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import org.libnoctis.render.gl.GlTexture;
import org.libnoctis.util.Vector2i;

/**
 * The Noctis Nine Patch
 *
 * <p>
 *     A Libnoctis version of the nine patch. Based on the android
 *     official nine patch, it can be directly created by an image,
 *     and generated to a BufferedImage or a GlTexture.
 *
 *     Use {@link NoctisNinePatchCache} to load a nine patch.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisNinePatch extends NinePatch
{
    /**
     * The generation cache, to keep the generated patch
     */
    private final HashMap<Vector2i, GlTexture> cache = new HashMap<Vector2i, GlTexture>();

    /**
     * The Noctis Nine Patch
     *
     * @param image The nine patch image
     */
    NoctisNinePatch(BufferedImage image)
    {
        super(image);
    }

    /**
     * Generate the nine patch for the given dimensions
     *
     * @param dimensions The dimensions of the image to generate
     *
     * @return The generated image
     */
    public GlTexture generateFor(Vector2i dimensions)
    {
        if (cache.containsKey(dimensions))
            return cache.get(dimensions);

        int width = dimensions.getX();
        int height = dimensions.getY();

        BufferedImage image = GraphicsUtilities.createCompatibleImage(width, height);
        this.draw((Graphics2D) image.getGraphics(), 0, 0, width, height);

        GlTexture texture = new GlTexture(image);
        cache.put(dimensions, texture);

        return texture;
    }

    /**
     * Generate the nine patch for the given dimensions
     *
     * @param width The width of the image to generate
     * @param height The height of the image to generate
     *
     * @return The generated image
     */
    public GlTexture generateFor(int width, int height)
    {
        return generateFor(new Vector2i(width, height));
    }
}
