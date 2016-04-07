package org.libnoctis.test;


import java.io.File;
import java.io.IOException;

import org.libnoctis.components.NFrame;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;
import org.lwjgl.LWJGLUtil;


public class NoctisTest
{
	/**
	 * Starts the game.
	 */
	public static void main(String[] args)
	{
		if (!init())
		{
			System.err.println("Couldn't load LWJGL, aborting.");
			return;
		}

		NFrame frame = new NFrame("Salut") {
			{
				registerListener(new Listener());
			}

			private boolean hovered;

			class Listener
			{
				@NoctisEvent
				public void mouseMoved(MouseMoveEvent event)
				{
					if (!hovered && event.getPos().length() < 50)
					{
						hovered = true;
						repaint();
					}
					else if (hovered && event.getPos().length() >= 50)
					{
						hovered = false;
						repaint();
					}
				}
			}

			@Override
			protected void paintComponent(Drawer drawer)
			{
				if (hovered)
				{
					drawer.setColor(Color.RED);
				}
				else
				{
					drawer.setColor(Color.WHITE);
				}
				drawer.drawRect(0, 0, 100, 100);

				System.out.println("paint");
			}
		};

		frame.setWidth(100);
		frame.setHeight(200);
		frame.show();

		frame.add(new Button());
	}

	private static boolean init()
	{
		try
		{
			LWJGLSetup.load(getBaseWorkingDirectory());
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	private static File getBaseWorkingDirectory()
	{
		String identifier = "lwjgl";
		final String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (LWJGLUtil.getPlatform())
		{
			case LWJGLUtil.PLATFORM_LINUX:
				workingDirectory = new File(userHome, "." + identifier + "/");
				break;
			case LWJGLUtil.PLATFORM_WINDOWS:
				final String applicationData = System.getenv("APPDATA");
				final String folder = applicationData != null ? applicationData : userHome;

				workingDirectory = new File(folder, "." + identifier + "/");
				break;
			case LWJGLUtil.PLATFORM_MACOSX:
				workingDirectory = new File(userHome, "Library/Application Support/" + identifier);
				break;
			default:
				workingDirectory = new File(userHome, identifier + "/");
		}

		return workingDirectory;
	}
}
