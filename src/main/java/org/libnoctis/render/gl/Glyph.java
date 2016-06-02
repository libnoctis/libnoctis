package org.libnoctis.render.gl;

/**
 * A Glyph (character of a font)
 *
 * @author Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public class Glyph
{
    /**
     * Character's sprite width in pixels.
     */
    private int width;

    /**
     * Character's sprite height in pixels.
     */
    private int height;

    /**
     * How many pixels far we should render from the basic char pos. This
     * does not affect the next character position.
     */
    private int xPrevAdvance;

    /**
     * How many pixels far on x axis we should render the next character.
     */
    private int xAdvance;

    /**
     * How many pixels far from base character pos we should render this
     * character.
     */
    private int yPrevAdvance;

    /**
     * Character sprite.
     */
    private TextureRegion icon;

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getxPrevAdvance()
    {
        return xPrevAdvance;
    }

    public void setxPrevAdvance(int xPrevAdvance)
    {
        this.xPrevAdvance = xPrevAdvance;
    }

    public int getxAdvance()
    {
        return xAdvance;
    }

    public void setxAdvance(int xAdvance)
    {
        this.xAdvance = xAdvance;
    }

    public int getyPrevAdvance()
    {
        return yPrevAdvance;
    }

    public void setyPrevAdvance(int yPrevAdvance)
    {
        this.yPrevAdvance = yPrevAdvance;
    }

    public TextureRegion getIcon()
    {
        return icon;
    }

    public void setIcon(TextureRegion icon)
    {
        this.icon = icon;
    }
}