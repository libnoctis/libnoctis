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


import org.libnoctis.input.mouse.MouseButton;
import org.libnoctis.input.mouse.MouseDraggedEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.input.mouse.MouseReleasedEvent;
import org.libnoctis.render.NoctisFrameThread;
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

	public NFrame(String title)
	{
		this.title = title;
		this.frameThread = new NoctisFrameThread(this);
	}

	/**
	 * Checks if is resizable.
	 *
	 * @return true, if is resizable.
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
	public boolean show()
	{
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
					isVisible = true;
				}
				catch (LWJGLException e)
				{
					isVisible = false;
					getFrameThread().setRunning(false);
				}
			}
		});

		getFrameThread().start();
		return false;
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

	public void input()
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

		}
	}

	private int lastClickMoveHandledX;
	private int lastClickMoveHandledY;

	private long lastMouseClickTime;

	private MouseButton eventButton;

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
				dispatchEvent(new MouseReleasedEvent(mousePos, button));;
				eventButton = null;
				lastMouseClickTime = System.currentTimeMillis();
			}
			else if (eventButton != null && lastMouseClickTime > 0L)
			{
				dispatchEvent(new MouseDraggedEvent(mousePos, button, mouseDynamicPos, new Vector2i(lastClickMoveHandledX, lastClickMoveHandledY), System.currentTimeMillis() - lastMouseClickTime));;

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
}
