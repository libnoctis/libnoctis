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

import java.io.File;
import java.io.IOException;

/**
 * The Theme Loader
 *
 * <p>
 *     The Theme Loader can load a theme, and manage the
 *     current selected theme.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThemeLoader
{
    /**
     * The current theme
     */
    private NoctisTheme currentTheme;

    /**
     * Load a theme in the given file
     *
     * @param themeZip The file of the theme to load
     *
     * @return The load theme
     *
     * @throws ThemeLoadingException If it failed to load the theme (File
     *         doesn't exist
     *         or it thrown an IOException)
     */
    public NoctisTheme load(File themeZip) throws ThemeLoadingException
    {
        if (!themeZip.exists())
            throw new ThemeLoadingException("Given file " + themeZip.getAbsolutePath() + " doesn't exist");

        try
        {
            return new NoctisTheme(themeZip);
        }
        catch (IOException e)
        {
            throw new ThemeLoadingException("Can't load the theme " + themeZip.getAbsolutePath(), e);
        }
    }

    /**
     * Set the current theme
     *
     * @param theme The theme to set
     */
    public void set(NoctisTheme theme)
    {
        this.currentTheme = theme;
    }

    /**
     * @return The current theme
     */
    public NoctisTheme get()
    {
        return currentTheme;
    }
}
