package org.libnoctis.test;


import java.io.File;
import java.io.IOException;

import org.libnoctis.components.NFrame;
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
		
		System.out.println(Color.BLUE.toString());

		NFrame frame = new NFrame("Salut") {
			@Override
			protected void paintComponent(Drawer drawer)
			{
			}
		};

		frame.setWidth(640);
		frame.setHeight(480);
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
