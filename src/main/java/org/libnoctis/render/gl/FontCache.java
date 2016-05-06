package org.libnoctis.render.gl;


import java.awt.Font;
import java.util.SortedMap;
import java.util.TreeMap;


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
