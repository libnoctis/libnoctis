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
package org.libnoctis.layout.base;

import org.libnoctis.components.NComponent;
import org.libnoctis.layout.IllegalLayoutPropertyException;
import org.libnoctis.layout.NLayout;
import org.libnoctis.util.Vector2i;

/**
 * The Absolute Layout
 *
 * <p>
 *     This layout just draw components at a given position.
 * </p>
 *
 * @see AbsoluteLayoutProperty
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class AbsoluteLayout extends NLayout
{
    @Override
    public Vector2i[] getElementsPosition(NComponent[] components)
    {
        Vector2i[] positions = new Vector2i[components.length];

        for (int i = 0; i < positions.length; i++)
        {
            NComponent component = components[i];

            if (!(component.getPosition() instanceof AbsoluteLayoutProperty))
                throw new IllegalLayoutPropertyException(component, "AbsoluteLayoutProperty");

            positions[i] = ((AbsoluteLayoutProperty) component.getPosition()).getPosition();
        }

        return positions;
    }
}
