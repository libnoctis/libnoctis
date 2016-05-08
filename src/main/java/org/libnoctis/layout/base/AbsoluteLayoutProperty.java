package org.libnoctis.layout.base;

import org.libnoctis.layout.LayoutProperty;
import org.libnoctis.util.Vector2i;

/**
 * The Absolute Layout Property
 *
 * <p>
 *     Property for the Absolute Layout, contains just a position.
 * </p>
 *
 * @see AbsoluteLayout
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class AbsoluteLayoutProperty extends LayoutProperty
{
    /**
     * The set position
     */
    private Vector2i position;

    /**
     * The Absolute Layout Property
     *
     * @param x The x position to set
     * @param y The y position to set
     */
    public AbsoluteLayoutProperty(int x, int y)
    {
        this.position = new Vector2i(x, y);
    }

    /**
     * The Absolute Layout Property
     *
     * @param position The position to set
     */
    public AbsoluteLayoutProperty(Vector2i position)
    {
        this.position = position;
    }

    /**
     * @return The position set
     */
    public Vector2i getPosition()
    {
        return position;
    }
}
