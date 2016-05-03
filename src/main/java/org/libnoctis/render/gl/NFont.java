package org.libnoctis.render.gl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.libnoctis.render.Drawer;
import org.libnoctis.render.NTexture;
import org.libnoctis.render.gl.TextureRegion.Builder;

/**
 * A Noctis Font
 *
 *
 * The Noctis Font object, very ugly as from now because
 * we haven't got time so, don't jugde please.
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class NFont
{
	/**
	 * A character display informations.
	 */
	private class Glyph
	{
		/**
		 * Character width in pixels.
		 */
		private int width;

		/**
		 * Character height in pixels.
		 */
		private int height;

		/**
		 * Character texture.
		 */
		private TextureRegion icon;

		public Glyph(int w, int h, TextureRegion icon)
		{
			this.width = w;
			this.height = h;
			this.icon = icon;
		}
	}

	/**
	 * Font size, in pixels.
	 */
	private int fontSize;

	/**
	 * The font texture (where the characters are)
	 */
	private transient NTexture fontTexture;

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

	/**
	 * If the antialiasing is activated for this font
	 */
	private boolean antiAliasing = true;

	private NFont(List<Character> chars, Font font)
	{
		this.font = font;
		buildTexture(chars);
	}

	private void buildTexture(List<Character> chars)
	{
		glyphs = new HashMap<Character, NFont.Glyph>();
		textureWidth = textureHeight = 512;
		fontSize = font.getSize();

		int amountOfChars = chars.size();
		int cellSize = fontSize;
		int lineSize = textureWidth / cellSize;
		int columnSize = textureHeight / cellSize;

		System.out.println("fontSize = " + fontSize);
		System.out.println("amountOfChars = " + amountOfChars);
		System.out.println("textureWidth = " + textureWidth);
		System.out.println("textureHeight = " + textureHeight);
		System.out.println("cellSize = " + cellSize);
		System.out.println("lineSize = " + lineSize);
		System.out.println("columnSize = " + columnSize);

		if (lineSize * columnSize < amountOfChars)
			throw new IllegalStateException();

		BufferedImage imgTemp = new BufferedImage(textureWidth, textureHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics graphics = imgTemp.createGraphics();
		graphics.setColor(new Color(0, 0, 0, 1));
		graphics.fillRect(0, 0, textureWidth, textureHeight);

		TextureRegion.Builder iconBuilder = new Builder(textureWidth, textureHeight);

		int x = 0;
		int y = 0;
		int w, h;

		for (int i = 0; i < amountOfChars; i++)
		{
			Character ch = chars.get(i);

			BufferedImage charImage = getFontImage(ch);
			w = charImage.getWidth();
			h = charImage.getHeight();

			if (x > textureWidth - w)
			{
				x = 0;
				y += h;
			}

			graphics.drawImage(charImage, x, y, null);
			glyphs.put(ch, new Glyph(w, h, iconBuilder.build(x, y, w, h)));

			x += w;
		}

		fontTexture = new NTexture(imgTemp);
	}

	private BufferedImage getFontImage(char ch)
	{
		// Create a temporary image to extract the character's size
		BufferedImage tempfontImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) tempfontImage.getGraphics();

		if (antiAliasing)
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(font);

		FontMetrics fontMetrics = g.getFontMetrics();
		int charwidth = fontMetrics.charWidth(ch);
		if (charwidth <= 0)
			charwidth = 7;

		int charheight = fontMetrics.getHeight();
		if (charheight <= 0)
			charheight = fontSize;

		// Create another image holding the character we are creating
		BufferedImage fontImage = new BufferedImage(charwidth, charheight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gt = (Graphics2D) fontImage.getGraphics();

		if (antiAliasing)
			gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		gt.setFont(font);
		gt.setColor(Color.WHITE);

		int charx = 0;
		int chary = 0;

		gt.drawString(String.valueOf(ch), (charx), (chary) + fontMetrics.getAscent());

		return fontImage;
	}

	/**
	 * Draw a string
	 *
	 * @param str The string to draw
	 * @param x The x position where to draw the string
	 * @param y The y position where to draw the string
     * @param drawer The drawer to use to render the string
     */
	public void drawString(String str, int x, int y, Drawer drawer)
	{
		fontTexture.bind();

		for (char ch : str.toCharArray())
			x += drawChar(ch, x, y, drawer);

		NTexture.bindNone();
	}

	/**
	 * Draw just a characters
	 *
	 * @param ch The character to draw
	 * @param x The x position where to draw the character
	 * @param y The y position where to draw the character
	 * @param drawer The drawer to use to render the character
	 *
     * @return The drawn character width
     */
	public int drawChar(char ch, int x, int y, Drawer drawer)
	{
		if (ch == ' ')
			return (int) (fontSize * 0.3f);

		Glyph glyph = glyphs.get(ch);
		drawer.drawTexture(x, y, glyph.width, glyph.height, fontTexture, glyph.icon);

		return glyph.width;
	}

	/**
	 * Create the same font with an other size
	 *
	 * @param size The size of the new font
	 *
	 * @return The generated font
     */
	public NFont derive(float size)
	{
		return NFont.fromAwt(this.font.deriveFont(size));
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
		for (int i = 0; i < 256; i++)
			CHARS.add((char) i);
	}

	/**
	 * Create a Noctis font from an AWT font
	 *
	 * @param font The AWT font to use to create the Noctis font
	 *
	 * @return The created Noctis font
     */
	public static NFont fromAwt(Font font)
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
	public static NFont fromAwt(Font font, List<Character> chars)
	{
		return new NFont(chars, font);
	}
}
