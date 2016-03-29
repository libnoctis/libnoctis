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
package org.libnoctis.components;

import java.util.ArrayList;

/**
 * The Noctis Container
 *
 * <p>
 *     The Noctis Container is a component that can contains other components.
 *     By example, NPanel is a NContainer
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class NContainer extends NComponent
{
    /**
     * The components in this container
     */
    private ArrayList<NComponent> components = new ArrayList<NComponent>();

    /**
     * @return The components in this container
     */
    public NComponent[] components()
    {
        NComponent[] components = new NComponent[numberOfComponents()];
        return this.components.toArray(components);
    }

    /**
     * @return The number of component in this container
     */
    public int numberOfComponents()
    {
        return components.size();
    }

    /**
     * Check if a component is in this container
     *
     * @param component The component to check if it is in this container
     *
     * @return True if it is, false if not
     */
    public boolean contains(NComponent component)
    {
        return components.contains(component);
    }

    /**
     * Add a component to this container
     *
     * @param component The component to add
     *
     * @return This
     */
    public NContainer add(NComponent component)
    {
        this.components.add(component);
        repaint();

        return this;
    }

    /**
     * Return the component of the given index
     *
     * @param index The index of the component to find
     *
     * @return The component with the given index
     */
    public NComponent get(int index)
    {
        return components.get(index);
    }

    /**
     * Remove from this container the component with the given index
     *
     * @param index The index of the component to remove
     *
     * @return The removed component
     */
    public NComponent remove(int index)
    {
        NComponent component = components.remove(index);
        repaint();

        return component;
    }

    /**
     * Remove a given component
     *
     * @param component The component to remove
     *
     * @return True if the component was in this container, false if not
     */
    public boolean remove(NComponent component)
    {
        boolean result = components.remove(component);
        repaint();

        return result;
    }

    @Override
    protected final void onFrame()
    {
        for (NComponent c : components)
            c.onFrame();

        onLoop();
    }

    /**
     * Same as on onFrame, but currently used for other things
     */
    protected abstract void onLoop();
}
