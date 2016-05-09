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
package org.libnoctis.components;


import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.File;

import org.libnoctis.input.keyboard.Key;
import org.libnoctis.input.keyboard.KeyPressedEvent;
import org.libnoctis.input.keyboard.KeyReleasedEvent;
import org.libnoctis.input.mouse.MouseButton;
import org.libnoctis.input.mouse.MouseDraggedEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.input.mouse.MouseReleasedEvent;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.DirectDrawer;
import org.libnoctis.render.gl.NoctisFrameThread;
import org.libnoctis.theme.NoctisTheme;
import org.libnoctis.theme.ThemeLoader;
import org.libnoctis.theme.ThemeLoadingException;
import org.libnoctis.util.Vector2i;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


/**
 * Base class for Noctis frames.
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class NFrame extends NContainer
{
    /**
     * This frame title.
     */
    private String title;

    /**
     * The frame current theme
     */
    private NoctisTheme theme;

    /**
     * {@code true} is the display was created.
     */
    private transient boolean isVisible;

    /**
     * {@code true} if this frame is undecorated.
     */
    private boolean undecorated;

    /**
     * This frame render Thread.
     */
    private NoctisFrameThread frameThread;

    /**
     * {@code true} if this frame is resizable.
     */
    private boolean isResizable;

    /**
     * The drawer object for this frame, and all its children.
     */
    private Drawer drawer;

    /**
     * Creates a new NFrame.
     *
     * @param title The frame title.
     */
    public NFrame(String title)
    {
        this(title, new DirectDrawer());
    }

    /**
     * Creates a new NFrame.
     *
     * @param title The frame title.
     * @param drawer This frame drawer.
     */
    public NFrame(String title, Drawer drawer)
    {
        this.title = title;
        this.frameThread = new NoctisFrameThread(this);
        this.drawer = drawer;
        getFrameThread().runLater(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    displayUpdateTitle();
                    displayUpdateDisplayMode();
                    displayCreate();
                    mouseCreate();
                    keyboardCreate();
                    resize();
                    isVisible = true;
                }
                catch (LWJGLException e)
                {
                    isVisible = false;
                    getFrameThread().setRunning(false);
                }
            }
        });
    }

    @Override
    public Drawer getDrawer()
    {
        return drawer;
    }

    @Override
    public NoctisTheme theme() throws RuntimeException
    {
        if (theme == null)
            throw new RuntimeException("Theme not set! Use loadTheme!");

        return theme;
    }

    /**
     * Load a noctis theme for this frame from the given zip
     *
     * @param zipTheme The theme zip
     */
    public void loadTheme(File zipTheme) throws ThemeLoadingException
    {
        ThemeLoader loader = new ThemeLoader();
        loader.load(zipTheme);

        this.theme = loader.get();
    }

    /**
     * Checks if is resizable.
     *
     * @return {@code true}, if is resizable, {@code false} otherwise.
     */
    public boolean isResizable()
    {
        return isResizable;
    }

    /**
     * Defines if this frame is resizable.
     *
     * @param isResizable the new value of resizable.
     */
    public void setResizable(boolean isResizable)
    {
        this.isResizable = isResizable;

        if (isVisible)
        {
            displayUpdateResizable();
        }
    }

    /**
     * Gets the frame title.
     *
     * @return This frame title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Checks if this frame is undecorated.
     *
     * @return {@code true} if this frame is undecorated;
     */
    public boolean isUndecorated()
    {
        return undecorated;
    }

    /**
     * Defines if this frame is undecorated. This operation will throw an
     * IllegalStateException is the window is already shown.
     *
     * @param undecorated {@code true} if the frame should appears undecorated.
     */
    public void setUndecorated(boolean undecorated)
    {
        if (!isVisible)
        {
            this.undecorated = undecorated;
            System.setProperty("org.lwjgl.opengl.Window.undecorated", String.valueOf(undecorated));
        }
    }

    /**
     * Gets this frame render Thread.
     *
     * @return This frame render Thread.
     */
    public NoctisFrameThread getFrameThread()
    {
        return frameThread;
    }

    /**
     * Adds this frame to the desktop, and starts render Thread.
     */
    public void show()
    {
        getFrameThread().start();
    }

    /**
     * Sets the frame title.
     *
     * @param title The new title value.
     */
    public void setTitle(String title)
    {
        this.title = title;

        if (isVisible)
        {
            displayUpdateTitle();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NFrame getFrame()
    {
        return this;
    }

    /**
     * Called from render Thread when frame close is requested.
     */
    public final void requestClose()
    {
        System.exit(0);
    }

    /**
     * Called from render Thread to listen to user input.
     */
    public final void input()
    {
        lwjglInput();
    }

    public int getFPS()
    {
        return 60;
    }

    // --- LWJGL implement ---
    private void lwjglInput()
    {
        while (Mouse.next())
        {
            lwjglMouseInput();
        }

        while (Keyboard.next())
        {
            lwjglKeyboardInput();
        }
    }

    private int lastClickMoveHandledX;
    private int lastClickMoveHandledY;

    private long lastMouseClickTime;

    private MouseButton eventButton;

    private Key eventKey;
    private long lastPressTime = 0;

    private void lwjglKeyboardInput()
    {
        Key key = Key.byCode(Keyboard.getEventKey());

        if (Keyboard.getEventKeyState())
        {
            dispatchEvent(new KeyPressedEvent(key));

            lastPressTime = System.currentTimeMillis();
            eventKey = key;
        }
        else if (eventKey != null)
        {
            dispatchEvent(new KeyReleasedEvent(key, System.currentTimeMillis() - lastPressTime));

            lastPressTime = 0;
            eventKey = null;
        }
    }

    private void lwjglMouseInput()
    {
        Vector2i mousePos = new Vector2i(Mouse.getX(), getHeight() - Mouse.getY() - 1);
        Vector2i mouseDynamicPos = new Vector2i(Mouse.getDX(), -Mouse.getDY());
        MouseButton button = MouseButton.byId(Mouse.getEventButton());

        if (Mouse.getEventButtonState())
        {
            lastClickMoveHandledX = mousePos.getX();
            lastClickMoveHandledY = mousePos.getY();
            dispatchEvent(new MousePressedEvent(mousePos, button));
            lastMouseClickTime = System.currentTimeMillis();
            eventButton = button;
        }
        else
        {
            if (button != null)
            {
                dispatchEvent(new MouseReleasedEvent(mousePos, button));
                eventButton = null;
                lastMouseClickTime = System.currentTimeMillis();
            }
            else if (eventButton != null && lastMouseClickTime > 0L)
            {
                dispatchEvent(new MouseDraggedEvent(mousePos, button, mouseDynamicPos, new Vector2i(lastClickMoveHandledX, lastClickMoveHandledY), System.currentTimeMillis() - lastMouseClickTime));
                lastClickMoveHandledX = mousePos.getX();
                lastClickMoveHandledY = mousePos.getY();
            }
            else
            {
                dispatchEvent(new MouseMoveEvent(mousePos, mouseDynamicPos));
            }
        }
    }

    private void displayUpdateTitle()
    {
        Display.setTitle(title);
    }

    private void displayUpdateResizable()
    {
        Display.setResizable(isResizable);
    }

    private void displayUpdateDisplayMode() throws LWJGLException
    {
        Display.setDisplayMode(new DisplayMode(getWidth(), getHeight()));
    }

    private void displayCreate() throws LWJGLException
    {
        Display.create();
    }

    private void mouseCreate() throws LWJGLException
    {
        Mouse.create();
    }

    private void keyboardCreate() throws LWJGLException
    {
        Keyboard.create();
    }

    public void resize()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, getWidth(), getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        // Enable alpha blending
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
}
