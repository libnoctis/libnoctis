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

import org.libnoctis.input.EventManager;
import org.libnoctis.layout.LayoutProperty;
import org.libnoctis.render.Drawer;
import org.libnoctis.util.Vector2i;

/**
 * A Noctis Component
 *
 * <p>
 *     The Noctis Component represents a graphic object, by example a button
 *     is a component. It contains all the commons components things, its size,
 *     its color, etc...
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class NComponent
{
    /**
     * The component dimension
     */
    private Vector2i size;

    /**
     * The component position, depending of the current layout.
     */
    private LayoutProperty position;

    /**
     * The parent container (that contains this component)
     */
    private NContainer parent;

    /**
     * The event manager
     */
    private EventManager manager;

    /**
     * @return The parent container (that contains this component)
     */
    public NContainer getParent()
    {
        return parent;
    }

    /**
     * @return The current drawer object
     */
    public Drawer getDrawer()
    {
        return parent.getDrawer();
    }

    /**
     * Register an event listener for this component events
     *
     * @param listener The event listener to add
     */
    public void registerListener(Object listener)
    {
        manager.registerListener(listener);
    }

    /**
     * Paint the component, for the first time.
     *
     * @param drawer The current drawer (implemented by the used render lib)
     */
    public abstract void paint(Drawer drawer);

    /**
     * Update the component (when some of its properties had changed, or something)
     *
     * @param drawer The current drawer (implemented by the used render lib)
     */
    protected abstract void repaint(Drawer drawer);

    /**
     * Executed on each frame (ex: executed 60 times per second if there is currently
     * 60 fps)
     */
    protected abstract void onFrame();

    /**
     * Repaints this component (update it)
     */
    public void repaint()
    {
        repaint(getDrawer());
    }

    /**
     * Executed when this component is added to a container, to setup things
     *
     * @param parent The container where this component was added
     */
    final void onAdded(NContainer parent)
    {
        this.parent = parent;

        onComponentAdded(parent);
    }

    /**
     * Executed when this component is added to a container
     *
     * @param parent The container where this component was added
     */
    protected void onComponentAdded(NContainer parent)
    {
    }

    /**
     * @return The size of the component
     */
    public Vector2i getSize()
    {
        return size;
    }

    /**
     * Set the size of the component, and repaint it
     *
     * @param size The new component size
     */
    public void setSize(Vector2i size)
    {
        this.size = size;
        repaint();
    }

    /**
     * @return The component position, corresponding to the current layout
     */
    public LayoutProperty getPosition()
    {
        return position;
    }

    /**
     * Set the component position
     *
     * @param position The position, depending to the current layout
     */
    public void setPosition(LayoutProperty position)
    {
        this.position = position;
        repaint();
    }
}