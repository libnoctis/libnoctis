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
package org.libnoctis.components.base;

import java.awt.image.BufferedImage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.libnoctis.components.NComponent;
import org.libnoctis.ninepatch.LinkedNinePatch;
import org.libnoctis.ninepatch.NoctisNinePatch;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;
import org.libnoctis.theme.ThemeRequireProperty;
import org.libnoctis.util.Vector2i;

/**
 * The NProgressBar
 *
 * <p>
 *     The Noctis progress bar, simply textured.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NProgressBar extends NComponent
{
    public static final String BAR_PROPERTY_GROUP = "component.progressbar";
    public static final String BAR_TEXTURE_PROPERTY = BAR_PROPERTY_GROUP + ".texture";
    public static final String BAR_FILLED_TEXTURE_PROPERTY = BAR_PROPERTY_GROUP + ".texture.filled";

    /**
     * The background texture of the bar, as a nine patch
     */
    @Nullable
    @LinkedNinePatch("backgroundTexture")
    @ThemeRequireProperty(BAR_TEXTURE_PROPERTY)
    private NoctisNinePatch backgroundPatch;

    /**
     * The background texture of the bar
     */
    @NotNull
    private GlTexture backgroundTexture;

    /**
     * The filling of the bar, cut depending on the value
     * and the maximum.
     */
    @NotNull
    @ThemeRequireProperty(BAR_FILLED_TEXTURE_PROPERTY)
    private BufferedImage filledTexture;

    /**
     * The last generated filling texture
     */
    private GlTexture lastGeneratedTexture;

    /**
     * The dimensions of the last generated filling texture
     */
    private Vector2i lastGeneratedDimensions;

    /**
     * The value of the progress bar. By example, if the value
     * is 50 and the maximum 100, the bar will be filled at the half of the end.
     */
    private int value;

    /**
     * The maximum value of the progress bar. By example, if the value
     * is 50 and the maximum 100, the bar will be filled at the half of the end.
     */
    private int maximum;

    @Override
    public void init()
    {
        updateFill();
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        drawer.drawTexture(this.getX(), this.getY(), this.getWidth(), this.getHeight(), backgroundTexture);
        drawer.drawTexture(this.getX(), this.getY(), getFillingWidth(), this.getHeight(), lastGeneratedTexture);
    }

    private void updateFill()
    {
        Vector2i dimensions = new Vector2i(getFillingWidth(), this.getHeight());

        if (dimensions.getX() == this.lastGeneratedDimensions.getX() && dimensions.getY() == this.lastGeneratedDimensions.getY())
            return;

        this.lastGeneratedTexture = new GlTexture(filledTexture.getSubimage(0, 0, dimensions.getX(), dimensions.getY()));
        this.lastGeneratedDimensions = dimensions;
    }

    private int getFillingWidth()
    {
        return (int) (((float) this.value / (float) this.maximum) * 100);
    }

    @Override
    public void setWidth(int width)
    {
        super.setWidth(width);

        updateFill();
    }

    @Override
    public void setHeight(int height)
    {
        super.setHeight(height);

        updateFill();
    }

    /**
     * @return The value of the progress bar. By example, if the value
     * is 50 and the maximum 100, the bar will be filled at the half of the end.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Set the value of the progress bar. By example, if the value
     * is 50 and the maximum 100, the bar will be filled at the half of the end.
     *
     * @param value The new value
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * @return The maximum value of the progress bar. By example, if the value
     * is 50 and the maximum 100, the bar will be filled at the half of the end.
     */
    public int getMaximum()
    {
        return maximum;
    }

    /**
     * Set the maximum value of the progress bar. By example, if the value
     * is 50 and the maximum 100, the bar will be filled at the half of the end.
     *
     * @param maximum The new maximum value
     */
    public void setMaximum(int maximum)
    {
        this.maximum = maximum;
    }
}
