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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The Noctis Chunk Generator
 *
 * <p>
 *     Generate a nine patch chunk from an image.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisChunkGenerator
{
    /**
     * The image to analyze to generate the chunk
     */
    private BufferedImage image;

    /**
     * An empty chunk that will be filled
     */
    private NoctisNinePatchChunk chunk = new NoctisNinePatchChunk();

    /**
     * The Noctis Chunk Generator
     *
     * @param image The image to analyze
     */
    public NoctisChunkGenerator(BufferedImage image)
    {
        this.image = image;
    }

    /**
     * Generate the chunk from the given image
     *
     * @return The generated chunk
     */
    public NoctisNinePatchChunk generate()
    {
        // Parsing image
        ImageParser parser = new ImageParser(image);

        int[] rows = parser.rows();
        int[] columns = parser.columns();

        // Getting the patches
        PatchesGroup rowsPatches = new PatchesGroup(rows);
        PatchesGroup columnsPatches = new PatchesGroup(columns);

        this.chunk.setHorizontalStartsWithPatch(rowsPatches.doesStartWithPatch());
        this.chunk.setVerticalStartsWithPatch(columnsPatches.doesStartWithPatch());

        rowsPatches.read();
        columnsPatches.read();

        // Getting the patches rectangles
        this.chunk.setPatches(getRectangles(rowsPatches.getFixed(), columnsPatches.getFixed()));
        this.chunk.setFixed(getRectangles(rowsPatches.getPatches(), columnsPatches.getPatches()));

        // Getting the horizontal/vertical patches
        if (!this.chunk.getFixed().isEmpty())
        {
            this.chunk.setHorizontalPatches(getRectangles(rowsPatches.getFixed(), columnsPatches.getPatches()));
            this.chunk.setVerticalPatches(getRectangles(rowsPatches.getPatches(), columnsPatches.getFixed()));
        }
        else if (!rowsPatches.getFixed().isEmpty())
            this.chunk.setVerticalPatches(getVerticalRectangles(rowsPatches.getFixed()));
        else if (!columnsPatches.getFixed().isEmpty())
            this.chunk.setHorizontalPatches(getHorizontalRectangles(columnsPatches.getFixed()));

        // New parsing
        parser.update();

        rows = parser.rows();
        columns = parser.columns();

        // New patches
        rowsPatches.setPixels(rows);
        columnsPatches.setPixels(columns);

        rowsPatches.read();
        columnsPatches.read();

        // Getting the padding
        this.chunk.setHorizontalPadding(getPadding(rowsPatches.getFixed()));
        this.chunk.setVerticalPadding(getPadding(columnsPatches.getFixed()));

        return this.chunk;
    }

    private List<Rectangle> getRectangles(List<Vector2i> lefts, List<Vector2i> tops)
    {
        List<Rectangle> rectangles = new ArrayList<Rectangle>();

        for (Vector2i left : lefts)
        {
            int y = left.getX();
            int height = left.getY() - left.getX();

            for (Vector2i top : tops)
            {
                int x = top.getX();
                int width = top.getY() - x;

                rectangles.add(new Rectangle(x, y, width, height));
            }
        }

        return rectangles;
    }

    private List<Rectangle> getVerticalRectangles(List<Vector2i> tops)
    {
        List<Rectangle> rectangles = new ArrayList<Rectangle>();

        for (Vector2i top : tops)
        {
            int x = top.getX();
            int width = top.getY() - x;

            rectangles.add(new Rectangle(x, 0, width, image.getHeight() - 2));
        }

        return rectangles;
    }

    private List<Rectangle> getHorizontalRectangles(List<Vector2i> tops)
    {
        List<Rectangle> rectangles = new ArrayList<Rectangle>();

        for (Vector2i top : tops)
        {
            int y = top.getX();
            int height = top.getY() - y;

            rectangles.add(new Rectangle(0, y, image.getWidth() - 2, height));
        }

        return rectangles;
    }

    private Vector2i getPadding(List<Vector2i> list)
    {
        if (list.isEmpty())
            return new Vector2i(0, 0);

        Vector2i vec = list.get(0);
        int padding = vec.getY() - vec.getX();

        if (list.size() == 1)
            if (vec.getX() == 0)
                return new Vector2i(padding, 0);
            else
                return new Vector2i(0, padding);
        else
        {
            Vector2i last = list.get(list.size() - 1);
            return new Vector2i(padding, last.getY() - last.getX());
        }
    }

    /**
     * @return The generated chunk (empty if #generate() wasn't launched)
     */
    public NoctisNinePatchChunk getChunk()
    {
        return chunk;
    }

    /**
     * @return The given image to be analyzed
     */
    public BufferedImage getImage()
    {
        return image;
    }
}
