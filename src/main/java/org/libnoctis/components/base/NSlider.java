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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.libnoctis.components.NComponent;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.input.mouse.MouseReleasedEvent;
import org.libnoctis.ninepatch.LinkedNinePatch;
import org.libnoctis.ninepatch.NoctisNinePatch;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;
import org.libnoctis.util.Vector2i;
import org.lwjgl.input.Mouse;

/**
 * The Noctis Slider
 *
 * <p>
 *     A simple slider
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.1.0
 */
public class NSlider extends NComponent
{
    public static final String SLIDER_PROPERTY_GROUP = "component.slider";
    public static final String SLIDER_SLIDE_TEXTURE_PROPERTY = SLIDER_PROPERTY_GROUP + ".texture.slide";
    public static final String SLIDER_SLIDER_TEXTURE_PROPERTY = SLIDER_PROPERTY_GROUP + ".texture.slider";

    /**
     * The slide (background) texture, as a nine patch
     */
    @Nullable
    @LinkedNinePatch("slideTexture")
    private NoctisNinePatch slidePatch;

    /**
     * The slide (background) texture
     */
    @NotNull
    private GlTexture slideTexture;

    /**
     * The slider (little thing on the slide) texture, as a nine patch
     */
    @Nullable
    @LinkedNinePatch("sliderTexture")
    private NoctisNinePatch sliderPatch;

    /**
     * The slider (little thing on the slide) texture
     */
    @NotNull
    private GlTexture sliderTexture;

    /**
     * The value of the slider. By example, if the value
     * is 50 and the maximum 100, the slide will be at the half position.
     */
    private int value;

    /**
     * The maximum value of the slider. By example, if the value
     * is 50 and the maximum 100, the slide will be at the half position.
     */
    private int maximum;

    /**
     * The theme property with the path of the slider texture
     */
    @NotNull
    private String sliderProperty;

    /**
     * The theme property with the path of the slide texture
     */
    @NotNull
    private String slideProperty;

    /**
     * The Noctis Slider
     */
    public NSlider()
    {
        this(0, 1);
    }

    /**
     * The Noctis Slider
     *
     * @param value The value of the slider. By example, if the value
     * is 50 and the maximum 100, the slide will be at the half position.
     * @param maximum The maximum value of the slider. By example, if the value
     * is 50 and the maximum 100, the slide will be at the half position.
     */
    public NSlider(int value, int maximum)
    {
        this(value, maximum, SLIDER_SLIDE_TEXTURE_PROPERTY, SLIDER_SLIDER_TEXTURE_PROPERTY);
    }

    /**
     * The Noctis Slider
     *
     * @param slideProperty The theme property with the path of the slide texture
     * @param sliderProperty The theme property with the path of the slider texture (little thing on the slide)
     */
    public NSlider(@NotNull String slideProperty, @NotNull String sliderProperty)
    {
        this(0, 1, slideProperty, sliderProperty);
    }

    /**
     * The Noctis Slider
     *
     * @param value The value of the slider. By example, if the value
     * is 50 and the maximum 100, the slide will be at the half position.
     * @param maximum The maximum value of the slider. By example, if the value
     * is 50 and the maximum 100, the slide will be at the half position.
     * @param slideProperty The theme property with the path of the slide texture
     * @param sliderProperty The theme property with the path of the slider texture (little thing on the slide)
     */
    public NSlider(int value, int maximum, @NotNull String slideProperty, @NotNull String sliderProperty)
    {
        this.value = value;
        this.maximum = maximum;
        this.slideProperty = slideProperty;
        this.sliderProperty = sliderProperty;
    }

    @Override
    protected void init()
    {
        super.init();

        this.registerNinePatch("slidePatch", theme().requireProp(slideProperty));
        this.registerNinePatch("sliderPatch", theme().requireProp(sliderProperty));

        this.registerListener(new NSliderListener());
    }

    private class NSliderListener implements NListener
    {
        private boolean doUpdate = true;

        @NoctisEvent
        private void press(MousePressedEvent event)
        {
            doUpdate = true;

            update(new Vector2i(event.getPos().getX() - getX(), event.getPos().getY() - getY()));
        }

        @NoctisEvent
        private void release(MouseReleasedEvent event)
        {
            doUpdate = false;
        }

        @NoctisEvent
        private void move(MouseMoveEvent event)
        {
            if (doUpdate)
                update(new Vector2i(event.getPos().getX() - getX(), event.getPos().getY() - getY()));
        }
    }

    private void update(Vector2i position)
    {
        int tickSize = this.getWidth() / this.maximum;

        this.value = Math.round((float) position.getX() / (float) tickSize);
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        drawer.drawTexture(this.getX(), this.getY(), this.getWidth(), this.getHeight(), slideTexture);
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;

        update(new Vector2i(Mouse.getX() - getX(), Mouse.getY() - getY()));
        invalidate();
    }

    public int getMaximum()
    {
        return maximum;
    }

    public void setMaximum(int maximum)
    {
        this.maximum = maximum;

        update(new Vector2i(Mouse.getX() - getX(), Mouse.getY() - getY()));
        invalidate();
    }
}
