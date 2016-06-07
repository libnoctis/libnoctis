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
package org.libnoctis.render.gl;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;
import org.libnoctis.util.Pair;
import org.libnoctis.util.Vector2i;

import com.android.ninepatch.GraphicsUtilities;


/**
 * A Noctis Font
 *
 * <p>
 * The Noctis Font object, very ugly as from now because we haven't got time so,
 * don't jugde please.
 * </p>
 *
 * @author Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public class GlFont
{
    /**
     * Font size, in pixels.
     */
    private int fontSize;

    /**
     * The font texture (where the characters are)
     */
    private transient GlTexture fontTexture;

    /**
     * The width of the font texture
     */
    private transient int textureWidth;

    /**
     * The height of the font texture
     */
    private transient int textureHeight;

    /**
     * This font glyphs (characters as texture)
     */
    private Map<Character, Glyph> glyphs;

    /**
     * The AWT fonts version of this font
     */
    private Font font;

    private GlFont(List<Character> chars, Font font)
    {
        this.font = font;
        buildTexture(chars);
    }

    private void buildTexture(List<Character> alphabet)
    {
        glyphs = new HashMap<Character, Glyph>();
        int amountOfChars = alphabet.size();
        fontSize = font.getSize();

        textureWidth = textureHeight = 1024;

        Graphics2D g2d = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        FontMetrics fontMetrics = g2d.getFontMetrics(font);

        BufferedImage imgTemp = GraphicsUtilities.createTranslucentCompatibleImage(textureWidth, textureHeight);
        Graphics2D graphics = imgTemp.createGraphics();
        TextureRegion.Builder iconBuilder = new TextureRegion.Builder(textureWidth, textureHeight);

        baseCharHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();

        int x = 0;
        int y = 0;
        int w, h;

        int maxHeight = 0;

        for (int i = 0; i < amountOfChars; i++)
        {
            Character ch = alphabet.get(i);

            Pair<Glyph, BufferedImage> pair = buildChar(ch, fontMetrics, graphics);
            if (pair != null)
            {
                BufferedImage charImage = pair.getValue();
                Glyph glyph = pair.getKey();

                w = glyph.getWidth();
                h = glyph.getHeight();

                if (x > textureWidth - w)
                {
                    x = 0;
                    y += maxHeight;
                    maxHeight = 0;
                }

                graphics.drawImage(charImage, x, y, null);

                glyph.setIcon(iconBuilder.build(x, y, w, h));

                glyphs.put(ch, glyph);

                x += w;

                if (maxHeight < h)
                {
                    maxHeight = h;
                }
            }
        }

        graphics.dispose();

        fontTexture = new GlTexture(imgTemp);
    }

    int baseCharHeight;

    Vector2i temp = new Vector2i(0, 0);

    /**
     * Creates a character texture and display information.
     * 
     * @param ch The character to be computed.
     * @param fontMetrics The global font metrics.
     * @param spriteSheetGraphics The sprite sheet graphics that will be used to
     *        render the character sprite to the sheet.
     *
     * @return The character display informations and its texture.
     */
    private Pair<Glyph, BufferedImage> buildChar(char ch, FontMetrics fontMetrics, Graphics2D spriteSheetGraphics)
    {
        if (!shouldTryRendering(ch))
        {
            return null;
        }

        String str = String.valueOf(ch);

        Rectangle2D bounds = fontMetrics.getStringBounds(str, spriteSheetGraphics);

        if (bounds.getWidth() <= 0)
        {
            str = "?";
            bounds = fontMetrics.getStringBounds(str, spriteSheetGraphics);
        }

        Glyph glyph = new Glyph();

        int boundsWidth = (int) (bounds.getWidth());
        int boundsHeight = (int) (bounds.getHeight());

        BufferedImage charImage = GraphicsUtilities.createTranslucentCompatibleImage(boundsWidth * 4, baseCharHeight);

        Graphics2D charGraphics = charImage.createGraphics();

        charGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        charGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        charGraphics.setFont(font);
        charGraphics.setColor(java.awt.Color.WHITE);

        charGraphics.drawString(str, (int) (boundsWidth * 0.5f), fontMetrics.getMaxAscent());
        charGraphics.dispose();

        Rectangle2D defaultBounds = new Rectangle2D.Float((charImage.getWidth() - boundsWidth) / 2, (charImage.getHeight() - boundsHeight) / 2, boundsWidth, boundsHeight);

        charImage = minimizeImage(charImage, defaultBounds, temp, false, true);

        glyph.setyPrevAdvance(0);
        glyph.setxAdvance((int) Math.floor(bounds.getWidth() - 1));
        glyph.setxPrevAdvance(-Math.max(0, (int) (defaultBounds.getX() - temp.getX()) - glyph.getxAdvance()) + 1);
        glyph.setWidth(charImage.getWidth());
        glyph.setHeight(charImage.getHeight());

        return Pair.of(glyph, charImage);
    }

    /**
     * Crops an image to remove translucent border if any.
     * 
     * @param big The image to be cropped.
     * @param defaultBounds The default bounds of the cropped image, if the big
     *        is totally translucent.
     * @param pos Vector to store the little texture left-top corner position in
     *        big texture coords.
     * @param defaultBoundsXTest {@code true} if the little texture's width has
     *        to be greater than {@code defaultBounds} width, {@code false}
     *        otherwise.
     * @param defaultBoundsYTest {@code true} if the little texture's height has
     *        to be greater than {@code defaultBounds} height, {@code false}
     *        otherwise.
     *
     * @return The cropped image.
     */
    private BufferedImage minimizeImage(BufferedImage big, Rectangle2D defaultBounds, Vector2i pos, boolean defaultBoundsXTest, boolean defaultBoundsYTest)
    {
        int bigWidth = big.getWidth();
        int bigHeight = big.getHeight();

        int x1 = bigWidth;
        int y1 = bigHeight;

        int[] pixel = new int[4];

        for (int i = 0; i < bigWidth; i++)
        {
            for (int j = 0; j < bigHeight; j++)
            {
                big.getRaster().getPixel(i, j, pixel);

                if (sum(pixel) != 0)
                {
                    if (i < x1)
                    {
                        x1 = i;
                    }

                    if (j < y1)
                    {
                        y1 = j;
                    }
                }
            }
        }

        int x2 = x1;
        int y2 = y1;

        for (int i = bigWidth - 1; i >= x1; i--)
        {
            for (int j = bigHeight - 1; j >= y1; j--)
            {
                big.getRaster().getPixel(i, j, pixel);

                if (sum(pixel) != 0)
                {
                    if (i > x2)
                    {
                        x2 = i;
                    }

                    if (j > y2)
                    {
                        y2 = j;
                    }
                }
            }
        }

        if (x1 == x2 || y1 == y2)
        {
            BufferedImage little = GraphicsUtilities.createTranslucentCompatibleImage((int) defaultBounds.getWidth(), (int) defaultBounds.getHeight());

            return little;
        }
        else
        {
            if (defaultBoundsXTest)
            {
                if (x1 > defaultBounds.getMinX())
                {
                    x1 = (int) defaultBounds.getMinX();
                }

                if (x2 < defaultBounds.getMaxX())
                {
                    x2 = (int) defaultBounds.getMaxX();
                }
            }

            if (defaultBoundsYTest)
            {
                if (y1 > defaultBounds.getMinY())
                {
                    y1 = (int) defaultBounds.getMinY();
                }

                if (y2 < defaultBounds.getMaxY())
                {
                    y2 = (int) defaultBounds.getMaxY();
                }
            }

            pos.setX(x1);
            pos.setY(y1);

            // Do not crop the last pixel column.
            x2 += 1;
            y2 += 1;

            BufferedImage little = GraphicsUtilities.createTranslucentCompatibleImage(x2 - x1, y2 - y1);

            Graphics2D littleGraphics = little.createGraphics();

            littleGraphics.drawImage(big, 0, 0, little.getWidth(), little.getHeight(), x1, y1, x2, y2, null);
            littleGraphics.dispose();

            return little;
        }
    }

    /**
     * Sums the elements of the given array.
     * 
     * @param array The array to be computed.
     *
     * @return The sum value of the array elements.
     */
    private static int sum(int[] array)
    {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
        {
            sum += array[i];
        }

        return sum;
    }

    /**
     * Draws a string.
     *
     * @param str The string to be drawn.
     * @param x The drawing X position.
     * @param y The drawing Y position.
     * @param drawer The drawer to use to render the string.
     */
    public void drawString(String str, int x, int y, Drawer drawer)
    {
        Vector2i pos = new Vector2i(x, y);

        char[] charArray = str.toCharArray();

        drawer.pushMatrix();

        Color color = drawer.getColor();

        lastTranslateX = 0;

        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];

            if (i != charArray.length - 1)
            {
                if (ch == '§')
                {
                    if (i == 0 || charArray[i - 1] != '\\')
                    {
                        // Parse color code.
                        String colorString = str.substring(i + 1);

                        int skippedChars = 0;
                        if (colorString.startsWith("("))
                        {
                            colorString = colorString.substring(1, colorString.indexOf(')'));
                            skippedChars = colorString.length() + 2;
                        }
                        else
                        {
                            skippedChars = 1;
                            colorString = colorString.substring(0, 1);
                        }

                        Color newColor = Color.valueOf(colorString);

                        if (newColor != null)
                        {
                            drawer.setColor(newColor);
                        }

                        i += skippedChars;
                        continue;
                    }
                }
                else if (ch == '\\')
                {
                    if (charArray[i + 1] == '§')
                    {
                        continue;
                    }
                }
            }

            drawChar(ch, pos, x, y, i, drawer);
        }

        drawer.setColor(color);

        lastTranslateX = 0;

        drawer.popMatrix();
    }

    /*
     * Used when a string creates a new line.
     */
    private transient int lastTranslateX;

    public int getStringWidth(String str)
    {
        Vector2i pos = new Vector2i(0, 0);
        int width = 0;
        String[] lines = str.split("\n");

        for (int i = 0; i < lines.length; i++)
        {
            pos.setX(0);
            pos.setY(0);

            char[] line = lines[i].toCharArray();

            for (int j = 0; j < line.length; j++)
            {
                char ch = line[j];

                if (j == 0)
                {
                    if (shouldTryRendering(ch))
                    {
                        Glyph glyph = glyphs.get(ch);
                        pos.add(-glyph.getxPrevAdvance(), 0);
                    }
                }

                if (j != line.length - 1)
                {
                    if (ch == '§')
                    {
                        if (j == 0 || line[j - 1] != '\\')
                        {
                            // Parse color code.
                            String colorString = str.substring(j + 1);

                            int skippedChars = 0;
                            if (colorString.startsWith("("))
                            {
                                colorString = colorString.substring(1, colorString.indexOf(')'));
                                skippedChars = colorString.length() + 2;
                            }
                            else
                            {
                                skippedChars = 1;
                                colorString = colorString.substring(0, 1);
                            }

                            j += skippedChars;
                            continue;
                        }
                    }
                    else if (ch == '\\')
                    {
                        if (line[j + 1] == '§')
                        {
                            continue;
                        }
                    }
                }

                addCharSize(ch, pos, 0, 0);

                if (pos.getX() > width)
                {
                    width = pos.getX();
                }
            }
        }

        return width;
    }

    public Rectangle2D getStringBounds(String str, int x, int y)
    {
        Vector2i pos = new Vector2i(0, 0);
        int width = 0;
        String[] lines = str.split("\n");
        int height = lines.length * baseCharHeight;

        for (int i = 0; i < lines.length; i++)
        {
            pos.setX(0);
            pos.setY(0);

            char[] line = lines[i].toCharArray();

            for (int j = 0; j < line.length; j++)
            {
                char ch = line[j];

                if (j == 0)
                {
                    if (shouldTryRendering(ch))
                    {
                        Glyph glyph = glyphs.get(ch);
                        pos.add(-glyph.getxPrevAdvance(), 0);
                    }
                }

                if (j != line.length - 1)
                {
                    if (ch == '§')
                    {
                        if (j == 0 || line[j - 1] != '\\')
                        {
                            // Parse color code.
                            String colorString = str.substring(j + 1);

                            int skippedChars = 0;
                            if (colorString.startsWith("("))
                            {
                                colorString = colorString.substring(1, colorString.indexOf(')'));
                                skippedChars = colorString.length() + 2;
                            }
                            else
                            {
                                skippedChars = 1;
                                colorString = colorString.substring(0, 1);
                            }

                            j += skippedChars;
                            continue;
                        }
                    }
                    else if (ch == '\\')
                    {
                        if (line[j + 1] == '§')
                        {
                            continue;
                        }
                    }
                }

                addCharSize(ch, pos, x, y);

                if (pos.getX() > width)
                {
                    width = pos.getX();
                }
            }
        }

        return new Rectangle2D.Float(x, y, width, height);
    }

    public int getFontSize()
    {
        return fontSize;
    }

    public void drawChar(char ch, Vector2i pos, int origX, int origY, int index, Drawer drawer)
    {
        if (shouldTryRendering(ch))
        {
            Glyph glyph = glyphs.get(ch);
            if (glyph == null)
                return;

            // We don't want to apply glyph.xPrevAdvance for the first character of a line.
            if (pos.getX() == origX)
            {
                drawer.translate(-lastTranslateX, 0);
                drawer.translate(lastTranslateX = -glyph.getxPrevAdvance(), 0);
            }

            drawer.drawTexture(pos.getX() + glyph.getxPrevAdvance(), pos.getY() + glyph.getyPrevAdvance(), glyph.getWidth(), glyph.getHeight(), fontTexture, glyph.getIcon());
        }

        addCharSize(ch, pos, origX, origY);
    }

    /**
     * @return {@code true} if there is any glyph registered for this character.
     */
    protected boolean shouldTryRendering(char ch)
    {
        return ch != ' ' && ch != '\t' && ch != '\n';
    }

    /**
     * @return
     */
    protected void addCharSize(char ch, Vector2i vector, int origX, int origY)
    {
        if (ch == ' ')
        {
            vector.add((int) (fontSize * 0.3f), 0);
        }
        else if (ch == '\t')
        {
            vector.add((int) (fontSize * 1.2f), 0);
        }
        else if (ch == '\n')
        {
            vector.setX(origX);
            vector.add(0, baseCharHeight);
        }
        else if (glyphs.containsKey(ch))
        {
            Glyph glyph = glyphs.get(ch);
            vector.add(glyph.getxAdvance(), 0);
        }
    }

    /**
     * Create the same font with an other size
     *
     * @param size The size of the new font
     *
     * @return The generated font
     */
    public GlFont derive(float size)
    {
        return GlFont.fromAwt(this.font.deriveFont(size));
    }

    /**
     * @return The awt version if this font
     */
    public Font getAWTFont()
    {
        return font;
    }

    /**
     * The list of all the existing characters
     */
    public static final List<Character> CHARS = new ArrayList<Character>();

    static
    {
        // Creating a character list
        for (int i = 32; i < 256; i++)
        {
            if (i >= 127 && i <= 159)
            {

            }
            else
            {
                CHARS.add((char) i);
            }
        }
    }

    /**
     * Create a Noctis font from an AWT font
     *
     * @param font The AWT font to use to create the Noctis font
     *
     * @return The created Noctis font
     */
    static GlFont fromAwt(Font font)
    {
        return fromAwt(font, CHARS);
    }

    /**
     * Create a Noctis font from an AWT font
     *
     * @param font The AWT font to use to create the Noctis font
     * @param chars The characters to load to the font
     *
     * @return The created Noctis font
     */
    static GlFont fromAwt(Font font, List<Character> chars)
    {
        return new GlFont(chars, font);
    }

    public void release()
    {
        fontTexture.release();
    }
}
