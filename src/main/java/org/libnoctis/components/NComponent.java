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

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.libnoctis.input.EventManager;
import org.libnoctis.input.NEvent;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.ninepatch.LinkedNinePatch;
import org.libnoctis.ninepatch.NoctisNinePatch;
import org.libnoctis.ninepatch.NoctisNinePatchCache;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;
import org.libnoctis.theme.NoctisTheme;
import org.libnoctis.theme.ThemeProperty;
import org.libnoctis.theme.ThemeRequireProperty;
import org.libnoctis.util.Dimension;
import org.libnoctis.util.Vector2i;


/**
 * A Noctis Component
 *
 * <p>
 * The Noctis Component represents a graphic object, by example a button is a
 * component. It contains all the commons components things, its size, its
 * color, etc...
 * </p>
 *
 * @author Litarvan & Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class NComponent
{
    public static final String COMPONENTS_SECTION = "component";

    /**
     * Display list id, used for rendering.
     */
    public int displayList = -1;

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
     * The component layout property, depending of the current layout.
     */
    private Object property;

    /**
     * The parent container (that contains this component)
     */
    private NContainer parent;

    /**
     * The event manager.
     */
    private EventManager manager;

    /**
     * The object properties
     */
    private Map<String, Object> properties;

    /**
     * This component parent frame.
     */
    private NFrame frame;

    /**
     * If the mouse is over this component
     */
    private boolean isHovered;

    /**
     * This component preferred size
     */
    private Dimension preferredSize;

    /**
     * The nine patches linked to textures, automatically managed
     */
    private HashMap<Field, Field> linkedPatches = new HashMap<Field, Field>();

    private Dimension minimumSize;

    private Dimension maximumSize;

    /**
     * The Noctis Component
     */
    public NComponent()
    {
        manager = new EventManager();
        properties = new HashMap<String, Object>();

        this.registerListener(new NComponentListener());
    }
    
    static final Object TREE_LOCK = new Object();
    
    public Object getTreeLock()
    {
        return TREE_LOCK;
    }

    /**
     * @return If the mouse is over this component
     */
    public boolean isHovered()
    {
        return isHovered;
    }

    public Map<String, Object> getProperties()
    {
        return properties;
    }

    private String getCallerClassName()
    {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();

        for (int i = 1; i < stElements.length; i++)
        {
            StackTraceElement ste = stElements[i];

            if (!ste.getClassName().equals(NComponent.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0)
            {
                return ste.getClassName();
            }
        }

        return null;
    }

    protected void registerNinePatch(String field, String pathInTheme)
    {
        if (pathInTheme == null)
            return;

        Class<?> cl;
        try
        {
            cl = Class.forName(getCallerClassName());
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException("Can't find the caller class of this method (" + getCallerClassName() + ")");
        }

        Field theField;
        try
        {
            theField = cl.getDeclaredField(field);
        }
        catch (NoSuchFieldException e)
        {
            throw new IllegalArgumentException("Can't find the field '" + field + "' in the component " + cl.getName());
        }

        LinkedNinePatch annotation = theField.getAnnotation(LinkedNinePatch.class);
        if (annotation == null)
            throw new IllegalArgumentException("The field '" + field + " in the component " + getClass().getName() + " hasn't the LinkedNinePatch annotation");

        Field texture;
        try
        {
            texture = cl.getDeclaredField(annotation.value());
        }
        catch (NoSuchFieldException e)
        {
            throw new IllegalArgumentException("Can't find the field '" + annotation.value() + "' in the component " + cl.getName() + " given in its LinkedNinePatch annotation");
        }

        if (pathInTheme.endsWith(".9.png"))
        {
            this.linkedPatches.put(theField, texture);

            theField.setAccessible(true);
            {
                try
                {
                    theField.set(this, NoctisNinePatchCache.fromPath(theme(), pathInTheme));
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            theField.setAccessible(false);

            updatePatch(theField, texture);
        }
        else
            try
            {
                texture.setAccessible(true);
                {
                    texture.set(this, theme().requireTexture(pathInTheme));
                }
                texture.setAccessible(false);
            }
            catch (IllegalAccessException ignored)
            {
                // Can't happen
            }
    }

    private void updatePatch(Field patch, Field texture)
    {
        try
        {
            NoctisNinePatch ninePatch;

            patch.setAccessible(true);
            {
                ninePatch = (NoctisNinePatch) patch.get(this);
            }
            patch.setAccessible(false);

            texture.setAccessible(true);
            {
                if (texture.getType() == GlTexture.class)
                    texture.set(this, ninePatch.generateFor(this.getWidth(), this.getHeight()));
                else if (texture.getType() == BufferedImage.class)
                    texture.set(this, ninePatch.raw(this.getWidth(), this.getHeight()));
            }
            texture.setAccessible(false);
        }
        catch (IllegalAccessException ignored)
        {
            // Can't happen
        }
    }

    private void updatePatches()
    {
        for (Entry<Field, Field> entry : linkedPatches.entrySet())
            updatePatch(entry.getKey(), entry.getValue());
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
            {
                paintComponent(drawer);
            }

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
        {
            paintComponent(drawer);
        }
        drawer.postPaint(this);
    }

    /**
     * Repaints this component (update it)
     */
    public final void repaint()
    {
        if (getFrame() == null)
            return;

        schedulRenderTask(new Runnable() {
            @Override
            public void run()
            {
                onRepaint();
                repaint(getDrawer());
            }
        });

        repaintChildren();
    }

    /**
     * Event called just befoire repainting
     */
    protected void onRepaint()
    {
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
                Class<?> cl = NComponent.this.getClass();

                while (cl != null)
                {
                    for (Field field : cl.getDeclaredFields())
                    {
                        fillProperty(field);
                    }

                    cl = cl.getSuperclass();
                }

                init();
            }
        });

        repaint();

        onComponentAdded(parent);
    }

    private String loadValueFromField(Field field)
    {
        String value = null;

        ThemeProperty annotation;
        if ((annotation = field.getAnnotation(ThemeProperty.class)) != null)
        {
            value = theme().prop(annotation.value());
        }

        ThemeRequireProperty requireAnnotation;
        if ((requireAnnotation = field.getAnnotation(ThemeRequireProperty.class)) != null)
        {
            value = theme().requireProp(requireAnnotation.value());
        }

        return value;
    }

    private void fillProperty(Field field)
    {
        String value = loadValueFromField(field);

        if (value == null)
            return;

        field.setAccessible(true);
        {
            try
            {
                if (field.getType().equals(boolean.class))
                {
                    field.set(this, Boolean.parseBoolean(value));
                }
                else if (field.getType().equals(int.class))
                {
                    field.set(this, Integer.parseInt(value));
                }
                else if (field.getType().equals(byte.class))
                {
                    field.set(this, Byte.parseByte(value));
                }
                else if (field.getType().equals(float.class))
                {
                    field.set(this, Float.parseFloat(value));
                }
                else if (field.getType().equals(double.class))
                {
                    field.set(this, Double.parseDouble(value));
                }
                else if (field.getType().equals(short.class))
                {
                    field.set(this, Short.parseShort(value));
                }
                else if (field.getType().equals(long.class))
                {
                    field.set(this, Long.parseLong(value));
                }
                else if (field.getType().equals(NoctisNinePatch.class))
                {
                    registerNinePatch(field.getName(), value);
                }
                else if (field.getType().equals(GlTexture.class))
                {
                    field.set(this, theme().requireTexture(value));
                }
                else if (field.getType().equals(BufferedImage.class))
                {
                    field.set(this, theme().requireImage(value));
                }
                else
                {
                    field.set(this, value);
                }
            }
            catch (IllegalAccessException ignored)
            {
                // Can't happen
            }
        }
        field.setAccessible(false);
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
     * @return The component X position
     */
    public int getX()
    {
        return x;
    }

    /**
     * Set the component X position
     *
     * @param x The new X position of the component
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * @return The component Y position
     */
    public int getY()
    {
        return y;
    }

    /**
     * Set the component Y position
     *
     * @param y The new Y position of the component
     */
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

        updatePatches();
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

        updatePatches();
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
     * @return The component layout property, corresponding to the current
     *         layout
     */
    public Object getLayoutConstraints()
    {
        return property;
    }

    /**
     * Set the component layout property (object to make the current layout
     * generate the property and the size of the component)
     *
     * @param property The property, depending to the current layout
     */
    public void setLayoutProperty(Object property)
    {
        this.property = property;

        repaint();
    }

    /**
     * Set the component position
     *
     * @param x The new x position of this component
     * @param y The new y position of this component
     */
    public void setPosition(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Set the component position
     *
     * @param position The new position of the component
     */
    public void setPosition(Vector2i position)
    {
        this.setPosition(position.getX(), position.getY());
    }

    /**
     * Set the component size
     *
     * @param width The new width of the component
     * @param height The new height of the component
     */
    public void setSize(int width, int height)
    {
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Set the component size
     *
     * @param dimensions The new dimensions=
     */
    public void setSize(Dimension dimensions)
    {
        this.setWidth(dimensions.getWidth());
        this.setHeight(dimensions.getHeight());
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
     * Dispatch an event to the event manager
     *
     * @param event The event to dispatch
     */
    public void dispatchEvent(NEvent event)
    {
        manager.callEvent(event);
    }

    public Dimension getPreferredSize()
    {
        return preferredSize;
    }

    public void setPreferredSize(Dimension preferredSize)
    {
        this.preferredSize = preferredSize;
        parent.invalidate();
    }

    public Dimension getMinimumSize()
    {
        return minimumSize;
    }

    public void setMinimumSize(Dimension minimumSize)
    {
        this.minimumSize = minimumSize;
    }

    public Dimension getMaximumSize()
    {
        return maximumSize;
    }

    public void setMaximumSize(Dimension maximumSize)
    {
        this.maximumSize = maximumSize;
    }

    /**
     * Used to update the hover state
     */
    private class NComponentListener implements NListener
    {
        @NoctisEvent
        private void move(MouseMoveEvent event)
        {
            isHovered = event.getPos().getX() > getX() && event.getPos().getX() < getX() + getWidth() &&

                    event.getPos().getY() > getY() && event.getPos().getY() < getY() + getHeight();
        }
    }

    public void setBounds(int x, int y, int w, int h)
    {
        setPosition(x, y);
        setSize(w, h);
    }
}