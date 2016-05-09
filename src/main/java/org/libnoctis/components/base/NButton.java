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


import org.jetbrains.annotations.Nullable;
import org.libnoctis.components.NComponent;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.input.mouse.MouseReleasedEvent;
import org.libnoctis.ninepatch.NinePatch;
import org.libnoctis.ninepatch.NoctisNinePatch;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;


/**
 * The Noctis Button
 * <p>
 * A button, can be clicked. Click click click.
 * Click click click click click click click click click.
 * Click click. Click.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NButton extends NComponent implements NListener
{
    public static final String BUTTON_TEXTURE_PROPERTY = "component.button.texture";
    public static final String BUTTON_HOVER_TEXTURE_PROPERTY = "component.button.texture.hover";
    public static final String BUTTON_DISABLED_TEXTURE_PROPERTY = "component.button.texture.disabled";

    /**
     * The button text
     */
    private String text;

    /**
     * The button font
     */
    // private NFont font;

    /**
     * If the button has fixed size
     */
    private boolean fixed;

    /**
     * The button texture, null if #texturePatch is not null
     */
    @Nullable
    private GlTexture texture;

    /**
     * The button texture as a nine patch, null if #texture is not null
     */
    @Nullable
    private NoctisNinePatch texturePatch;

    /**
     * The texture when the mouse is on the button
     */
    @Nullable
    private GlTexture hoverTexture;

    /**
     * The texture when the mouse is on the button, as a nine patch
     */
    @Nullable
    private NoctisNinePatch hoverTexturePatch;

    /**
     * The texture when the button is disabled
     */
    @Nullable
    private GlTexture disabledTexture;

    /**
     * The texture when the button is disabled, as a nine patch
     */
    @Nullable
    private NoctisNinePatch disabledTexturePatch;

    /**
     * True if the mouse is over this button
     */
    private boolean hover = false;

    /**
     * True if the button is clicked
     */
    private boolean clicked = false;

    /**
     * True if the button is disabled
     */
    private boolean disabled = false;

    /**
     * If the button's textures are nine patches
     */
    private boolean textureAreNinePatches;

    /**
     * The Noctis Button
     *
     * @param text The button text
     */
    public NButton(String text)
    {
        this.text = text;
        this.registerListener(this);
    }

    @Override
    protected void init()
    {
        super.init();

        this.fixed = Boolean.parseBoolean(theme().requireProp("component.button.size.fixed"));

        String texture = theme().requireProp(BUTTON_TEXTURE_PROPERTY);
        String textureHover = theme().requireProp(BUTTON_HOVER_TEXTURE_PROPERTY);
        String textureDisabled = theme().hasProperty(BUTTON_DISABLED_TEXTURE_PROPERTY) ? theme().requireProp(BUTTON_DISABLED_TEXTURE_PROPERTY) : null;

        if (texture.endsWith(".9.png"))
            textureAreNinePatches = true;

        if (textureAreNinePatches)
        {
            this.texturePatch = NinePatch.create(theme().requireImage(texture));
            this.hoverTexturePatch = NinePatch.create(theme().requireImage(textureHover));

            if (textureDisabled != null)
                this.disabledTexturePatch = NinePatch.create(theme().requireImage(textureDisabled));
        }
        else
        {

            this.texture = theme().requireTexture(texture);
            this.hoverTexture = theme().requireTexture(textureHover);

            if (textureDisabled != null)
                this.disabledTexture = theme().requireTexture(textureDisabled);
        }

        if (fixed)
        {
            this.setWidth(Integer.parseInt(theme().requireProp("component.button.size.width")));
            this.setHeight(Integer.parseInt(theme().requireProp("component.button.size.height")));
        }
    }

    @Override
    public void setWidth(int width)
    {
        if (!fixed)
            super.setWidth(width);
    }

    @Override
    public void setHeight(int height)
    {
        if (!fixed)
            super.setHeight(height);
    }

    /**
     * @return If the button is disabled
     */
    public boolean isDisabled()
    {
        return disabled;
    }

    /**
     * Set the button disabled or not
     *
     * @param disabled If the button should be disabled or not
     */
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    /**
     * @return If the button is clicked
     */
    public boolean isClicked()
    {
        return clicked;
    }

    /**
     * @return If the mouse is hover the button
     */
    public boolean isHover()
    {
        return hover;
    }

    /**
     * @return If the size of the button is fixed
     */
    public boolean isFixed()
    {
        return fixed;
    }

    /**
     * @return Return the button texture (can be null if the texture is a nine
     *         patch,
     *         if it is, use #getTexturePatch)
     */
    @Nullable
    public GlTexture getTexture()
    {
        return texture;
    }

    /**
     * @return The texture when the mouse is hover the button. (can be null if
     *         the
     *         the theme didn't give one, or if it is a nine patch (if it is,
     *         use #getHoverTexturePatch).
     */
    @Nullable
    public GlTexture getHoverTexture()
    {
        return hoverTexture;
    }

    /**
     * @return The button texture when it is disabled (can be null if the theme
     *         didn't give one, or if it is a nine patch (if it is, use
     *         #getDisabledTexturePatch).
     */
    @Nullable
    public GlTexture getDisabledTexture()
    {
        return disabledTexture;
    }

    /**
     * @return Return the button texture, as a nine patch (can be null if the
     *         texture is not a nine patch, if it is, use #getTexture)
     */
    @Nullable
    public NoctisNinePatch getTexturePatch()
    {
        return texturePatch;
    }

    /**
     * @return The texture when the mouse is hover the button, as a nine patch.
     *         (can be null if the the theme didn't give one, or if it is not a
     *         nine patch (if it is, use #getHoverTexture).
     */
    @Nullable
    public NoctisNinePatch getHoverTexturePatch()
    {
        return hoverTexturePatch;
    }

    /**
     * @return The button texture when it is disabled, as a nine patch (can be
     *         null
     *         if the theme didn't give one, or if it is not a nine patch (if it
     *         is,
     *         use #getDisabledTexture).
     */
    @Nullable
    public NoctisNinePatch getDisabledTexturePatch()
    {
        return disabledTexturePatch;
    }

    /**
     * @return If the textures are nine patch
     */
    public boolean areTexturesNinePatches()
    {
        return textureAreNinePatches;
    }

    /**
     * @return The button text
     */
    public String getText()
    {
        return text;
    }

    /**
     * Set the button text
     *
     * @param text The new button text
     */
    public void setText(String text)
    {
        this.text = text;

        repaint();
    }

    @NoctisEvent
    private void move(MouseMoveEvent event)
    {
        hover = event.getPos().getX() > getX() && event.getPos().getX() < getX() + getWidth() && event.getPos().getY() > getY() && event.getPos().getY() < getY() + getHeight();
    }

    @NoctisEvent
    private void click(MousePressedEvent event)
    {
        clicked = hover;
    }

    @NoctisEvent
    private void release(MouseReleasedEvent event)
    {
        clicked = false;
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        if (disabled && disabledTexture == null)
        {
            throw new RuntimeException("Can't set the button disabled because there isn't any disabled texture");
        }

        GlTexture toDraw;

        if (textureAreNinePatches)
        {
            toDraw = disabled ? disabledTexturePatch.generateFor(this.getWidth(), this.getHeight()) : (hover ? hoverTexturePatch.generateFor(this.getWidth(), this.getHeight()) : texturePatch.generateFor(this.getWidth(), this.getHeight()));
        }
        else
            toDraw = disabled ? disabledTexture : (hover ? hoverTexture : texture);

        drawer.drawTexture(getX(), getY(), this.getWidth(), this.getHeight(), toDraw);

        // TODO: Draw the text
        drawer.drawCenteredString(text, getX() + getWidth() / 2, getY() + getHeight() / 2);
    }
}
