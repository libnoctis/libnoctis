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

import org.libnoctis.input.EventManager;
import org.libnoctis.input.NEvent;
import org.libnoctis.input.NListener;
import org.libnoctis.layout.LayoutProperty;
import org.libnoctis.render.Drawer;
import org.libnoctis.theme.NoctisTheme;
import org.libnoctis.util.Vector2i;


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
	 * This component X coordinate, in pixels, relative to parent orthonormal.
	 */
	private int x;

	/**
	 * This component Y coordinate, in pixels, relative to parent orthonormal.
	 */
	private int y;

	/**
	 * The component position, depending of the current layout.
	 */
	private LayoutProperty position;

	/**
	 * The parent container (that contains this component)
	 */
	private NContainer parent;

	/**
	 * The event manager.
	 */
	private EventManager manager;

    /**
     * The layout generated position
     */
    private Vector2i generatedPosition;

    /**
     * The object properties
     */
	private Map<String, Object> properties;

	/**
	 * Display list id, used for rendering.
	 */
	public int displayList = -1;

	/**
	 * This component parent frame.
	 */
	private NFrame frame;

	private boolean isHovered;
	
	public NComponent()
	{
		manager = new EventManager();
		properties = new HashMap<String, Object>();
	}
	
	public void setHovered(boolean isHovered)
	{
		this.isHovered = isHovered;
	}
	
	public boolean isHovered()
	{
		return isHovered;
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
	 * @return The current theme
     */
	public NoctisTheme theme()
	{
		return parent.theme();
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
	public void registerListener(NListener listener)
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
			if (drawer.shouldPaintEveryFrame())
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

		repaintChildren();
	}

	/**
	 * Called by repaint to repaint children.
	 */
	protected void repaintChildren()
	{

	}

	/**
	 * Executed when this component is added to a container, to setup things
	 *
	 * @param parent The container where this component was added
	 */
	final void onAdded(NContainer parent)
	{
		this.parent = parent;

		NContainer parent2 = parent;

		while (parent2 != null)
		{
			if (parent2 instanceof NFrame)
			{
				frame = (NFrame) parent2;
				break;
			}
			else
			{
				parent2 = parent2.getParent();
			}
		}

		schedulRenderTask(new Runnable() {
			@Override
			public void run()
			{
				init();
			}
		});

		repaint();

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

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
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
		return frame;
	}

	/**
	 * Called when added to a container from the render Thread to init the
	 * component (load textures, fonts, etc.).
	 */
	protected void init()
	{

	}

    /**
     * Set the component position generated by the parent's layout.
     * Only Libnoctis can call that.
     *
     * @param generatedPosition The generated position
     */
    void setGeneratedPosition(Vector2i generatedPosition)
    {
        this.generatedPosition = generatedPosition;
    }

    /**
     * @return The layout generated position for this component
     */
    public Vector2i getGeneratedPosition()
    {
        return generatedPosition;
    }

	/**
	 * Dispatch an event to the event manager
	 *
	 * @param event The event to dispatch
     */
	public void dispatchEvent(NEvent event)
	{
		manager.callEvent(event);
	}
}