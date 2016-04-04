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


import java.util.HashMap;
import java.util.Map;

import org.libnoctis.input.Event;
import org.libnoctis.input.EventManager;
import org.libnoctis.layout.LayoutProperty;
import org.libnoctis.render.Drawer;


/**
 * A Noctis Component
 * <p>
 * The Noctis Component represents a graphic object, by example a button is a
 * component. It contains all the commons components things, its size, its
 * color, etc...
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class NComponent
{
	/**
	 * This component width, in pixels.
	 */
	private int width;

	/**
	 * This component height, in pixels.
	 */
	private int height;

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
	
	private Map<String, Object> properties;
	public int displayList = -1;

	public NComponent()
	{
		manager = new EventManager();
		properties = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getProperties()
	{
		return properties;
	}

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
		return parent == null ? null : parent.getDrawer();
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
	 * Called every frame from the parent frame's render Thread to render this
	 * component and its children to the screen.
	 */
	public final void render()
	{
		Drawer drawer = getDrawer();

		if (drawer != null)
		{
			if (drawer.paintEveryFrame())
				paintComponent(drawer);
			render(drawer);
		}
	}

	/**
	 * Called every frame from the parent frame's render Thread to render this
	 * component and its children to the screen.
	 */
	protected final void render(Drawer drawer)
	{
		renderComponent(drawer);
		renderChildren(drawer);
	}

	/**
	 * Called every frame from the parent frame's render Thread to render this
	 * component's children to the screen.
	 */
	protected void renderChildren(Drawer drawer)
	{

	}

	/**
	 * Called every frame from the parent frame's render Thread to render this
	 * component to the screen.
	 */
	protected void renderComponent(Drawer drawer)
	{
		drawer.render(this);
	}

	/**
	 * Paint the component, for the first time.
	 *
	 * @param drawer The current drawer (implemented by the used render lib)
	 */
	protected void paintComponent(Drawer drawer)
	{

	}

	/**
	 * Update the component (when some of its properties had changed, or
	 * something)
	 *
	 * @param drawer The current drawer (implemented by the used render lib)
	 */
	protected final void repaint(Drawer drawer)
	{
		drawer.prePaint(this);
		paintComponent(drawer);
		drawer.postPaint(this);
	}

	/**
	 * Repaints this component (update it)
	 */
	public final void repaint()
	{
		schedulRenderTask(new Runnable() {
			@Override
			public void run()
			{
				repaint(getDrawer());
			}
		});
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
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width)
	{
		this.width = width;
		invalidate();
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height)
	{
		this.height = height;
		invalidate();
	}

	/**
	 * Invalidates this component. An not valid component needs to be laid out
	 * again.
	 */
	public void invalidate()
	{
		// TODO : implement

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

	/**
	 * Schedule the given task to be executed in the render Thread.
	 * 
	 * @param runnable The task to be executed in the render Thread.
	 */
	protected void schedulRenderTask(Runnable runnable)
	{
		getFrame().getFrameThread().runLater(runnable);
	}

	/**
	 * Gets the frame that contains this component.
	 * 
	 * @return The frame that contains this component.
	 */
	public NFrame getFrame()
	{
		NComponent parent = this;

		while (parent != null)
		{
			if (parent instanceof NFrame)
			{
				return (NFrame) parent;
			}
			else
			{
				parent = parent.getParent();
			}
		}

		return null;
	}

	public void dispatchEvent(Event event)
	{
		manager.callEvent(event);
	}
}