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
package org.libnoctis.components;

import java.util.ArrayList;
import java.util.Iterator;

import org.libnoctis.layout.NLayout;
import org.libnoctis.render.Drawer;
import org.libnoctis.util.Vector2i;

/**
 * The Noctis Container
 *
 * <p>
<<<<<<< HEAD
 * The Noctis Container is a component that can contains other components.
 * For example, NPanel is a NContainer.
=======
 *     The Noctis Container is a component that can contains other components.
 *     By example, NPanel is a NContainer
>>>>>>> 3b25dd0f8b8aad633da27820a74d05667b2fb816
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
     * The components positions
     */
    private Vector2i[] positions = new Vector2i[0];

    /**
     * The container layout
     */
    private NLayout layout;

	/**
	 * @return The number of component in this container
	 */
	public int numberOfComponents()
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
        if (layout == null)
            throw new RuntimeException("Layout not set ! Use setLayout !");

		this.components.add(component);

		component.onAdded(this);
        this.positions = layout.getElementsPosition(this.components.toArray(new NComponent[this.components.size()]));

        component.setGeneratedPosition(this.positions[this.components.size() - 1]);
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
	public NComponent get(int index)
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
     * @return The components position, in order.
     */
    public Vector2i[] getPositions()
    {
        return positions;
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
		for (Iterator<NComponent> iterator = components.iterator(); iterator.hasNext();)
		{
			iterator.next().render();
		}
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
}
