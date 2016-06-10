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
package org.libnoctis.components;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.libnoctis.input.NEvent;
import org.libnoctis.layout.NLayout;
import org.libnoctis.render.Drawer;


/**
 * The Noctis Container
 *
 * <p>
 * The Noctis Container is a component that can contains other components. For
 * example, NPanel is a NContainer.
 * </p>
 *
 * @author Litarvan & Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public abstract class NContainer extends NComponent
{
    /**
     * The components in this container
     */
    private ArrayList<NComponent> components = new ArrayList<NComponent>();

    /**
     * The container layout
     */
    private NLayout layout;

    /**
     * The container insets.
     */
    @NotNull
    private Insets insets;

    public NContainer()
    {
        super();

        insets = new Insets(0, 0, 0, 0);
    }

    /**
     * @return This container insets.
     */
    public Insets getInsets()
    {
        return insets;
    }

    /**
     * Defines this container insets.
     * 
     * @param insets The new insets.
     */
    public void setInsets(Insets insets)
    {
        this.insets = insets;
        invalidate();
    }

    /**
     * @return The children components of this container.
     */
    public List<NComponent> getChildren()
    {
        return components;
    }

    /**
     * @return The number of component in this container
     */
    public int getComponentCount()
    {
        return components.size();
    }

    /**
     * Checks if a component is in this container.
     *
     * @param component The component to check if it is in this container.
     * @return {@code true} if it is, {@code false} if not.
     */
    public boolean contains(NComponent component)
    {
        return components.contains(component);
    }

    /**
     * Adds a component to this container.
     *
     * @param component The component to be added.
     * @return This for chaining.
     */
    public NContainer add(NComponent component) throws RuntimeException
    {
        this.components.add(component);

        component.setParent(this);

        if (layout != null)
            layout.layoutContainer(this);

        component.onAdded(this);

        repaint();

        return this;
    }

    /**
     * Return the component of the given index
     *
     * @param index The index of the component to find
     * @return The component with the given index
     */
    public NComponent getComponent(int index)
    {
        return components.get(index);
    }

    /**
     * Remove from this container the component with the given index
     *
     * @param index The index of the component to remove
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
     * @return True if the component was in this container, false if not
     */
    public boolean remove(NComponent component)
    {
        boolean result = components.remove(component);
        repaint();

        return result;
    }

    /**
     * {@inheritDoc}
     */
    protected void repaintChildren()
    {
        for (Iterator<NComponent> iterator = components.iterator(); iterator.hasNext();)
        {
            iterator.next().repaint();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void renderChildren(Drawer drawer)
    {
        if (components.size() < 1)
            return;

        NComponent[] comp = components.toArray(new NComponent[components.size()]);

        drawer.pushMatrix();
        {
            drawer.translate(getX(), getY());

            for (NComponent component : comp)
            {
                component.render();
            }
        }
        drawer.popMatrix();
    }

    /**
     * @return This container layout
     */
    public NLayout getLayout()
    {
        return layout;
    }

    /**
     * Set the layout of this container
     *
     * @param layout The new layout
     */
    public void setLayout(NLayout layout)
    {
        this.layout = layout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invalidate()
    {
        if (layout != null)
        {
            layout.layoutContainer(this);
        }

        super.invalidate();
    }

    @Override
    public void dispatchEvent(NEvent event)
    {
        super.dispatchEvent(event);

        NComponent[] array = components.toArray(new NComponent[components.size()]);

        for (NComponent component : array)
        {
            component.dispatchEvent(event);
        }
    }
}
