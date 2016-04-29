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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NinePatch
{
    public static final String FILE_EXTENSION = ".9.png";

    public static NoctisNinePatch create(File file) throws IOException
    {
        return create(ImageIO.read(file));
    }

    public static NoctisNinePatch create(BufferedImage image)
    {
        BufferedImage compatible = CompatibleImageMaker.convert(image);
        NoctisChunkGenerator generator = new NoctisChunkGenerator(image);
        NoctisNinePatchChunk chunk = generator.generate();
        BufferedImage real = compatible.getSubimage(1, 1, compatible.getWidth() - 2, compatible.getHeight() - 2);

        return new NoctisNinePatch(real, chunk);
    }
}
