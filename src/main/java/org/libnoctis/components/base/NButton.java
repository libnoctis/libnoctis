/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 * This file is part of Libnoctis.
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.components.base;


import org.jetbrains.annotations.Nullable;
import org.libnoctis.components.NComponent;
import org.libnoctis.input.NListener;
import org.libnoctis.components.NContainer;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.input.mouse.MouseReleasedEvent;
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
     * The button texture
     */
    private GlTexture texture;

    /**
     * The texture when the mouse is on the button
     */
    private GlTexture hoverTexture;

    /**
     * The texture when the button is disabled
     */
    @Nullable
    private GlTexture disabledTexture;

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
    protected void onComponentAdded(NContainer parent)
    {
        super.onComponentAdded(parent);

        this.fixed = Boolean.parseBoolean(theme().requireProp("button.size.fixed"));
        this.texture = theme().requireTexture(theme().requireProp("button.texture.normal"));
        this.hoverTexture = theme().requireTexture(theme().requireProp("button.texture.hover"));
        if (this.theme().hasProperty("button.texture.disabled"))
            this.disabledTexture = theme().requireTexture(theme().requireProp("button.texture.disabled"));

        if (fixed)
        {
            this.setWidth(Integer.parseInt(theme().requireProp("button.size.width")));
            this.setHeight(Integer.parseInt(theme().requireProp("button.size.height")));
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
     * @return Return the button texture
     */
    public GlTexture getTexture()
    {
        return texture;
    }

    /**
     * @return The texture when the mouse is hover the button
     */
    public GlTexture getHoverTexture()
    {
        return hoverTexture;
    }

    /**
     * @return The button texture when it is disabled (can be null if the theme
     *         didn't give one)
     */
    @Nullable
    public GlTexture getDisabledTexture()
    {
        return disabledTexture;
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
        hover = event.getPos().getX() > getGeneratedPosition().getX() && event.getPos().getX() < getGeneratedPosition().getX() + getWidth() && event.getPos().getY() > getGeneratedPosition().getY() && event.getPos().getY() < getGeneratedPosition().getY() + getHeight();
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

        drawer.drawTexture(getGeneratedPosition().getX(), getGeneratedPosition().getY(), this.getWidth(), this.getHeight(), disabled ? disabledTexture : (hover ? hoverTexture : texture));

        // TODO: Draw the text
    }
}
