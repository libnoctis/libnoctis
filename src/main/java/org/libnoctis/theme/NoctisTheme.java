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
package org.libnoctis.theme;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

import org.jetbrains.annotations.Nullable;
import org.libnoctis.render.gl.GlTexture;


/**
 * A Noctis Theme
 * <p>
 * A Noctis Theme is a zip containing some properties (in a properties file) and
 * some images.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisTheme
{
    /**
     * The folder where the textures are in the zip file.
     */
    public static final String TEXTURE_FOLDER = "textures/";

    /**
     * The zip file of the theme.
     */
    private ZipFile zip;

    /**
     * The theme properties.
     */
    private Properties properties = new Properties();

    /**
     * The Noctis Theme.
     *
     * @param zip The zip file of the theme.
     * @throws IOException If it failed to read the zip.
     */
    NoctisTheme(File zip) throws IOException
    {
        this.zip = new ZipFile(zip);

        // Read the properties from the theme.properties file of the zip
        properties.load(get("theme.properties"));
    }

    /**
     * Reads a file from the zip.
     *
     * @param path The path of the file (in the zip).
     * @return An input stream of the file.
     * @throws IOException If it failed to read the file.
     */
    public InputStream get(String path) throws IOException
    {
        ZipEntry entry = zip.getEntry(path);
        if (entry == null)
            throw new IOException("Can't find the entry " + path);

        return zip.getInputStream(entry);
    }

    /**
     * Reads a file from the zip and throws a {@code ThemeRequiredException} if
     * it couldn't find it.
     *
     * @param path The path of the file (in the zip).
     * @return An input stream of the file.
     * @throws ThemeRequiredException If it failed to read the file.
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
     * Reads an image from the zip.
     *
     * @param path The path of the image (in the textures folder of the zip).
     * @return The read buffered image.
     * @throws IOException If it failed to read the image.
     */
    @Nullable
    public BufferedImage image(String path)
    {
        return imageWithDefault(path, null);
    }

    /**
     * Reads an image from the zip. Returns the given default image if the key
     * couldn't be resolved.
     *
     * @param path The path of the image (in the textures folder of the zip).
     * @return The read buffered image.
     * @throws IOException If it failed to read the image.
     */
    public BufferedImage imageWithDefault(String path, BufferedImage defaultImage)
    {
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(get(TEXTURE_FOLDER + path));
        }
        catch (IOException e)
        {

        }

        return image == null ? defaultImage : image;
    }

    /**
     * Read an image from the zip and throws a {@code ThemeRequiredException}
     * if it couldn't find it.
     *
     * @param path The path of the image (in the textures folder of the zip).
     * @return The read buffered image.
     * @throws ThemeRequiredException If it failed to read the image.
     */
    public BufferedImage requireImage(String path)
    {
        if (!hasProperty(path))
        {
            throw new ThemeRequiredException("Can't find the required image '" + path + "'");
        }

        return image(path);
    }

    /**
     * Reads a texture from the zip. Returns {@code null} if the texture
     * couldn't be loaded or if the property doesn't exist.
     *
     * @param path The path of the texture (in the textures folder of the zip).
     * @return The read texture.
     * @throws IOException If an I/O error occurs while loading the texture.
     */
    @Nullable
    public GlTexture texture(String path)
    {
        return textureWithDefault(path, null);
    }

    /**
     * Reads a texture from the zip. Returns {@code null} if the texture
     * couldn't be loaded or if the property doesn't exist.
     *
     * @param path The path of the texture (in the textures folder of the zip).
     * @return The read texture.
     */
    public GlTexture textureWithDefault(String path, GlTexture defaultTexture)
    {
        BufferedImage image = null;

        try
        {
            image = requireImage(path);
        }
        catch (ThemeRequiredException ex)
        {

        }

        return image == null ? defaultTexture : new GlTexture(image);
    }

    /**
     * Read a texture from the zip and throws a {@code ThemeRequiredException}
     * if it couldn't find it.
     *
     * @param path The path of the texture (in the textures folder of the zip).
     * @return The read texture.
     * @throws ThemeRequiredException If the property couldn't be found.
     */
    public GlTexture requireTexture(String path)
    {
        if (!hasProperty(path))
        {
            throw new ThemeRequiredException("Couln't find required texture '" + path + "'");
        }

        return texture(path);
    }

    /**
     * Checks if the theme has the given property.
     *
     * @param key The property of the value to check.
     * @return {@code true} if this theme has the given property.
     */
    public boolean hasProperty(String key)
    {
        return properties.containsKey(key);
    }

    /**
     * Gets the theme property of the given key.
     *
     * @param key The key of the value to get.
     * @return The read value.
     * @throws ThemeRequiredException If the property couldn't be found.
     */
    public String requireProp(String key)
    {
        if (!hasProperty(key))
        {
            throw new ThemeRequiredException("Can't find the required property " + key + " in the current theme");
        }

        return followLink(properties.getProperty(key));
    }

    /**
     * Gets the theme property of the given key. If none, returns the given
     * default value.
     *
     * @param key The key of the value to get.
     * @param defaultValue The default value to return in case of the property
     *        couldn't be found.
     * @return The read value.
     */
    public String propWithDefault(String key, String defaultValue)
    {
        String prop = properties.getProperty(key);
        if (prop == null)
        {
            return defaultValue;
        }
        else
        {
            return followLink(prop);
        }
    }

    /**
     * Gets the theme property of the given key. If none, returns {@code null}.
     *
     * @param key The key of the value to get.
     * @return The read value.
     */
    @Nullable
    public String prop(String key)
    {
        return propWithDefault(key, null);
    }

    /**
     * Gets the theme integer of the given key. If none, returns {@code 0}.
     *
     * @param key The key of the integer to get.
     * @return The read value.
     */
    public int getInt(String key)
    {
        return getIntWithDefault(key, 0);
    }

    /**
     * Gets the theme integer of the given key.
     *
     * @param key The key of the value to get.
     * @return The read integer.
     * @throws ThemeRequiredException If the property couldn't be found.
     */
    public int requireInt(String key)
    {
        if (!hasProperty(key))
        {
            throw new ThemeRequiredException("Couln't find required integer '" + key + "'");
        }
        
        return getInt(key);
    }

    /**
     * Gets the theme integer of the given key. If none, returns the given
     * default value.
     *
     * @param key The key of the value to get.
     * @param defaultValue The default value to return in case of the property
     *        couldn't be found.
     * @return The read value.
     */
    public int getIntWithDefault(String key, int defaultValue)
    {
        if (!hasProperty(key))
        {
            return defaultValue;
        }
        
        try
        {
            return Integer.parseInt(prop(key));
        }
        catch (NumberFormatException ex)
        {
            return defaultValue;
        }
    }
    
    /**
     * Gets the theme boolean of the given key. If none, returns {@code false}.
     *
     * @param key The key of the integer to get.
     * @return The read value.
     */
    @Nullable
    public boolean getBoolean(String key)
    {
        return getBooleanWithDefault(key, false);
    }

    /**
     * Gets the theme boolean of the given key.
     *
     * @param key The key of the value to get.
     * @return The read integer.
     * @throws ThemeRequiredException If the property couldn't be found.
     */
    public boolean requireBoolean(String key)
    {
        if (!hasProperty(key))
        {
            throw new ThemeRequiredException("Couln't find required integer '" + key + "'");
        }
        
        return getBoolean(key);
    }

    /**
     * Gets the theme boolean of the given key. If none, returns the given
     * default value.
     *
     * @param key The key of the value to get.
     * @param defaultValue The default value to return in case of the property
     *        couldn't be found.
     * @return The read value.
     */
    public boolean getBooleanWithDefault(String key, boolean defaultValue)
    {
        if (!hasProperty(key))
        {
            return defaultValue;
        }
        
        try
        {
            return Boolean.parseBoolean(prop(key));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return defaultValue;
        }
    }

    private String followLink(String prop)
    {
        while (prop.startsWith("$"))
        {
            prop = prop(prop.substring(1));
        }

        while (prop.startsWith("\\$") || prop.startsWith("\\"))
        {
            prop = prop.substring(1);
        }
        return prop;
    }
}
