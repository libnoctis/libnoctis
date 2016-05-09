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
package org.libnoctis.theme;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.libnoctis.render.gl.GlTexture;

/**
 * A Noctis Theme
 *
 * <p>
 *     A Noctis Theme is a zip containing some properties (in a properties file) and
 *     some images.
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
     *
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
     *
     * @return An input stream of the file.
     *
     * @throws IOException If it failed to read the file.
     */
    @Nullable
    public InputStream get(@NotNull String path) throws IOException
    {
        ZipEntry entry = zip.getEntry(path);

        if (entry == null)
            return null;

        return zip.getInputStream(entry);
    }

    /**
     * Reads a file from the zip and throws a {@code ThemeRequiredException} if
     * it couldn't find it.
     *
     * @param path The path of the file (in the zip).
     *
     * @return An input stream of the file.
     *
     * @throws ThemeRequiredException If it failed to read the file.
     */
    @NotNull
    public InputStream require(@NotNull String path) throws ThemeRequiredException
    {
        try
        {
            InputStream stream = get(path);

            if (stream == null)
                throw new IOException("Can't find the file " + path);

            return stream;
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
     *
     * @return The read buffered image.
     *
     * @throws IOException If it failed to read the image.
     */
    @Nullable
    public BufferedImage image(@NotNull String path) throws IOException
    {
        if (path == null)
            throw new IllegalArgumentException("path == null");

        InputStream stream = get(TEXTURE_FOLDER + path);

        if (stream == null)
            return null;

        return ImageIO.read(stream);
    }

    /**
     * Read an image from the zip and throws a {@code ThemeRequiredException}
     * if it couldn't find it.
     *
     * @param path The path of the image (in the textures folder of the zip).
     *
     * @return The read buffered image.
     *
     * @throws ThemeRequiredException If it failed to read the image.
     */
    @NotNull
    public BufferedImage requireImage(@NotNull String path) throws ThemeRequiredException
    {
        try
        {
            BufferedImage image = image(path);

            if (image == null)
                throw new IOException("Can't find the file " + path);

            return image;
        }
        catch (IOException e)
        {
            throw new ThemeRequiredException("Can't find the required image " + path + " in the current theme", e);
        }
    }

    /**
     * Reads a texture from the zip.
     *
     * @param path The path of the texture (in the textures folder of the zip).
     *
     * @return The read texture.
     *
     * @throws IOException If it failed to read the texture.
     */
    @Nullable
    public GlTexture texture(@NotNull String path) throws IOException
    {
        BufferedImage image = image(path);

        if (image == null)
            return null;

        return new GlTexture(requireImage(path));
    }

    /**
     * Read a texture from the zip and throws a {@code ThemeRequiredException}
     * if it couldn't find it.
     *
     * @param path The path of the texture (in the textures folder of the zip)
     *
     * @return The read texture
     *
     * @throws ThemeRequiredException If it failed to read the texture
     */
    @NotNull
    public GlTexture requireTexture(@NotNull String path) throws ThemeRequiredException
    {
        try
        {
            GlTexture texture = texture(path);

            if (texture == null)
                throw new IOException("Can't find the file " + path);
        }
        catch (IOException e)
        {
            throw new ThemeRequiredException("Can't find the required texture " + path + " in the current theme", e);
        }
    }

    /**
     * Checks if the theme has the given property.
     *
     * @param key The property of the value to check.
     *
     * @return If the theme has the given property.
     */
    public boolean hasProperty(@NotNull String key)
    {
        if (key == null)
            throw new IllegalArgumentException("key == null");

        return properties.containsKey(key);
    }

    /**
     * Gets the theme property of the given key, or the default
     * value if the key doesn't exist.
     *
     * @param key The key of the value to get.
     * @param def The default value if the key doesn't exist
     *
     * @return The read value.
     */
    @Nullable
    public String prop(@NotNull String key, @Nullable String def)
    {
        if (key == null)
            throw new IllegalArgumentException("key == null");

        String prop = properties.getProperty(key);

        if (prop == null)
            return def;

        if (prop.startsWith("$"))
            prop = requireProp(prop.substring(1));

        if (prop.startsWith("\\$") || prop.startsWith("\\"))
            prop = prop.substring(1);

        return prop;
    }

    /**
     * Gets the theme property of the given key.
     *
     * @param key The key of the value to get.
     *
     * @return The read value.
     */
    @Nullable
    public String prop(@NotNull String key)
    {
        return prop(key, null);
    }

    /**
     * Get the theme property of the given key and throws a
     * {@code ThemeRequiredException} if it can't find it.
     *
     * @param key The key of the value to get.
     *
     * @return The read value.
     */
    public String requireProp(String key)
    {
        String prop = prop(key);

        if (prop == null)
            throw new ThemeRequiredException("Can't find the required property " + key + " in the current theme");

        return prop;
    }
}

