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
package org.libnoctis.render.gl;

import java.awt.Font;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Font Cache
 *
 * <p>
 *     The font cache keeps the generated font to re-use them.
 * </p>
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class FontCache
{
    /**
     * Font cache.
     */
    private static final SortedMap<String, GlFont> FONTS = new TreeMap<String, GlFont>();

    /**
     * Gets or creates the {@code GlFont} corresponding to the passed AWT font.
     * 
     * @param font
     * @return
     */
    public static GlFont getGlFont(Font font)
    {
        String key = font.getName() + "/" + font.getSize() + "/" + font.getStyle();

        synchronized (FONTS)
        {
            GlFont glFont = FONTS.get(key);
            if (glFont == null)
            {
                glFont = GlFont.fromAwt(font);
                FONTS.put(key, glFont);
            }
            return glFont;
        }
    }
}
