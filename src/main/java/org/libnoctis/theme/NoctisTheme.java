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
package org.libnoctis.theme;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

import org.libnoctis.render.gl.GlTexture;

/**
 * A Noctis Theme
 *
 * <p>
 *     A Noctis Theme is a zip containing some properties (in a json)
 *     and some images.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisTheme
{
    /**
     * The folder where the textures are in the zip file
     */
    public static final String TEXTURE_FOLDER = "textures/";

    /**
     * The zip file of the theme
     */
    private ZipFile zip;

    /**
     * The theme properties
     */
    private Properties     properties = new Properties();

    /**
     * The Noctis Theme
     *
     * @param zip The zip file of the theme
     *
     * @throws IOException If it failed to read the zip
     */
    NoctisTheme(File zip) throws IOException
    {
        this.zip = new ZipFile(zip);

        // Read the properties from the theme.properties file of the zip
        properties.load(get("theme.properties"));
    }

    /**
     * Read a file from the zip
     *
     * @param path The path of the file (in the zip)
     *
     * @return An input stream of the file
     *
     * @throws IOException If it failed to read the file
     */
    public InputStream get(String path) throws IOException
    {
        ZipEntry entry = zip.getEntry(path);
        return zip.getInputStream(entry);
    }

    /**
     * Read a file from the zip and throws a ThemeRequiredException (RuntimeException)
     * if it can't find it
     *
     * @param path The path of the file (in the zip)
     *
     * @return An input stream of the file
     *
     * @throws ThemeRequiredException If it failed to read the file
     */
    public InputStream require(String path) throws ThemeRequiredException
    {
        try
        {
            return get(path);
        }
        catch (IOException e)
        {
            throw new ThemeRequiredException("Can't find the required file " + path + " in the current theme", e);
        }
    }

    /**
     * Read an image from the zip
     *
     * @param path The path of the image (in the textures folder of the zip)
     *
     * @return The read buffered image
     *
     * @throws IOException If it failed to read the image
     */
    public BufferedImage image(String path) throws IOException
    {
        return ImageIO.read(get(TEXTURE_FOLDER + path));
    }

    /**
     * Read an image from the zip and throws a ThemeRequiredException (RuntimeException)
     * if it can't find it
     *
     * @param path The path of the image (in the textures folder of the zip)
     *
     * @return The read buffered image
     *
     * @throws ThemeRequiredException If it failed to read the image
     */
    public BufferedImage requireImage(String path) throws ThemeRequiredException
    {
        try
        {
            return image(path);
        }
        catch (IOException e)
        {
            throw new ThemeRequiredException("Can't find the required image " + path + " in the current theme", e);
        }
    }

    /**
     * Read a texture from the zip
     *
     * @param path The path of the texture (in the textures folder of the zip)
     *
     * @return The read texture
     *
     * @throws IOException If it failed to read the texture
     */
    public GlTexture texture(String path) throws IOException
    {
        return new GlTexture(image(path));
    }

    /**
     * Read a texture from the zip and throws a ThemeRequiredException (RuntimeException)
     * if it can't find it
     *
     * @param path The path of the texture (in the textures folder of the zip)
     *
     * @return The read texture
     *
     * @throws ThemeRequiredException If it failed to read the texture
     */
    public GlTexture requireTexture(String path) throws ThemeRequiredException
    {
        try
        {
            return texture(path);
        }
        catch (IOException e)
        {
            throw new ThemeRequiredException("Can't find the required texture " + path + " in the current theme", e);
        }
    }

    /**
     * Check if the theme has the given property
     *
     * @param key The property of the value to check
     *
     * @return If the theme has the given property
     */
    public boolean hasProperty(String key)
    {
        return properties.containsKey(key);
    }

    /**
     * Get the theme property of the given key
     *
     * @param key The key of the value to get
     *
     * @return The read value
     */
    public Object prop(String key)
    {
        return properties.getProperty(key);
    }

    /**
     * Get the theme property of the given key and throws a ThemeRequiredException
     * (RuntimeException) if it can't find it
     *
     * @param key The key of the value to get
     *
     * @return The read value
     */
    public String requireProp(String key)
    {
        String prop = properties.getProperty(key);
        if (prop == null)
            throw new ThemeRequiredException("Can't find the required property " + key + " in the current theme");

        return prop;
    }
}
