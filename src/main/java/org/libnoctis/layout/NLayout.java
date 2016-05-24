/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Libnoctis is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.layout;

import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;


/**
 * The Noctis Layout
 *
 * <p>
 * The Noctis layout is an objet that can manage the components positions of a
 * container
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @see LayoutConstraints
 * @since 1.0.0
 */
public abstract class NLayout
{
    /**
     * The parent that is using this layout instance
     */
    protected NContainer laidOutContainer;

    /**
     * A method executed when the layout is set to a container
     *
     * @param parent The container where this layout was added
     */
    final void onSet(NContainer parent)
    {
        this.laidOutContainer = parent;

        onLayoutSet(parent);
    }

    /**
     * A method executed when the layout is set to a container
     *
     * @param parent The container where this layout was added
     */
    protected void onLayoutSet(NContainer parent)
    {
        layoutContainer(parent);
    }

    /**
     * Update the position and the size of the given elements
     *
     * @param components The components to update the position and the size
     */
    public abstract void layoutContainer(NContainer parent);

    /**
     * Checks if the given {@link LayoutConstraints} object is applicable for this
     * layout to the given component.
     * 
     * @param properties The constraints to check.
     * @param component The component that holds the passed constraints.
     * @return {@code true} if this component can be added with the given
     *         constraints, {@code false} otherwise.
     */
    public abstract boolean areValidConstraints(LayoutConstraints properties, NComponent component);
}
