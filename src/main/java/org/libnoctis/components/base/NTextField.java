/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.

 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.components.base;

import org.libnoctis.components.NComponent;
import org.libnoctis.render.Drawer;

/**
 * The Noctis Text Field
 *
 * <p>
 *     A text field. Type type type.
 *     Type.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NTextField extends NComponent
{
    /**
     * The current text field text
     */
    private String text;

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);


    }

    /**
     * @return The current text field text
     */
    public String getText()
    {
        return text;
    }

    /**
     * Set the text field current text
     *
     * @param text The new field text
     */
    public void setText(String text)
    {
        this.text = text;
    }
}
