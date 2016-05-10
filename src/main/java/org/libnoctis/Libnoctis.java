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
package org.libnoctis;

import com.android.ninepatch.GraphicsUtilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Libnoctis main class
 *
 * <p>
 *     This class contains the version, and maybe some utils
 *     things by the future.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Libnoctis
{
    /**
     * The Libnoctis current version
     */
    public static final String VERSION = "1.0.0";

    private static void deleteAndResize(File[] files, int diviser) throws IOException
    {
        for (File file : files)
        {
            if (!file.getName().endsWith(".9.png"))
                continue;

            File newFile = new File(file.getAbsolutePath().replace(".9.png", ".png"));

            BufferedImage image = ImageIO.read(file);
            BufferedImage nonPatch = image.getSubimage(1, 1, image.getWidth() - 2, image.getHeight() - 2);
            BufferedImage resized = GraphicsUtilities.createTranslucentCompatibleImage(nonPatch.getWidth() / diviser, nonPatch.getHeight() / diviser);

            resized.getGraphics().drawImage(nonPatch, 0, 0, resized.getWidth(), resized.getHeight(), null);

            ImageIO.write(resized, "png", newFile);

            System.out.println(file.getAbsoluteFile() + " -> " + newFile.getAbsolutePath());

            file.delete();
        }
    }

    private static void multiplyNinePatch(File[] files, File ninePatch) throws IOException
    {
        for (File file : files)
        {
            if (file.getName().endsWith(".9.png") || !file.getName().endsWith(".png"))
                continue;

            File newFile = new File(file.getAbsolutePath().replace(".png", ".9.png"));

            BufferedImage image = ImageIO.read(file);
            BufferedImage patch = ImageIO.read(ninePatch);

            patch.getGraphics().drawImage(image, 1, 1, image.getWidth(), image.getHeight(), null);

            ImageIO.write(patch, "png", newFile);

            System.out.println(file.getAbsoluteFile() + " -> " + newFile.getAbsolutePath());

            file.delete();
        }
    }
}
