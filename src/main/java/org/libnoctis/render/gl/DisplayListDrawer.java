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
package org.libnoctis.render.gl;


import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;

import org.libnoctis.components.NComponent;


/**
 * The Display List Drawer
 * <p>
 * This drawer render things using directly GL and
 * a list of the things to render in their order of rendering.
 * </p>
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class DisplayListDrawer extends DirectDrawer
{
    @Override
    public void render(NComponent component)
    {
        glCallList(component.displayList);
    }

    @Override
    public void prePaint(NComponent component)
    {
        if (component.displayList != -1)
        {
            glDeleteLists(component.displayList, 1);
        }

        component.displayList = glGenLists(1);

        glNewList(component.displayList, GL_COMPILE);
    }

    @Override
    public void postPaint(NComponent component)
    {
        glEndList();
    }

    @Override
    public boolean shouldPaintEveryFrame()
    {
        return false;
    }
}
