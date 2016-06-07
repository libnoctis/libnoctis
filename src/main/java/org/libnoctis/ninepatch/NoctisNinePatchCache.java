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

import java.util.HashMap;
import org.libnoctis.theme.NoctisTheme;

/**
 * The Noctis Nine Patch Cache
 *
 * <p>
 *     This class contains all the nine patch registered, with a cache,
 *     to make not them be loaded every frame.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public final class NoctisNinePatchCache
{
    /**
     * The nine patch cache
     */
    private static final HashMap<String, NoctisNinePatch> cache = new HashMap<String, NoctisNinePatch>();

    /**
     * Return a nine patch from a theme property
     *
     * @param theme The current theme
     * @param property The theme property containing the path of the nine patch to load
     *
     * @return The loaded nine patch, or its cached version
     */
    public static NoctisNinePatch fromProperty(NoctisTheme theme, String property)
    {
        return fromPath(theme, theme.requireProp(property));
    }

    /**
     * Return a nine patch from a theme at the given path
     *
     * @param theme The current theme
     * @param path The path of the nine patch to load
     *
     * @return The loaded nine patch, or its cached version
     */
    public static NoctisNinePatch fromPath(NoctisTheme theme, String path)
    {
        if (cache.containsKey(path))
            return cache.get(path);

        NoctisNinePatch patch = new NoctisNinePatch(theme.requireImage(path));
        cache.put(path, patch);

        return patch;
    }
}
