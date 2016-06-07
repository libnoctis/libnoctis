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

import org.libnoctis.components.NComponent;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;

/**
 * The Noctis Image
 *
 * <p>
 *     A simple texture component.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.1.0
 */
public class NImage extends NComponent
{
    private String    textureProperty;
    private GlTexture texture;

    public NImage(String property)
    {
        this.textureProperty = property;
    }

    public NImage(GlTexture texture)
    {
        this.texture = texture;
    }

    @Override
    protected void init()
    {
        super.init();

        if (textureProperty != null)
            texture = theme().requireTexture(theme().requireProp(textureProperty));
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        drawer.drawTexture(this.getX(), this.getY(), this.getWidth(), this.getHeight(), texture);
    }

    public void setTexture(String property)
    {
        setTexture(theme().requireTexture(theme().requireProp(property)));
    }

    public void setTexture(GlTexture texture)
    {
        this.texture = texture;
    }

    public GlTexture getTexture()
    {
        return texture;
    }
}
