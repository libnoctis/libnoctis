package org.libnoctis.test;

import java.io.File;
import java.io.IOException;

import org.libnoctis.components.NFrame;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MouseDraggedEvent;
import org.libnoctis.input.mouse.MouseMoveEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.input.mouse.MouseReleasedEvent;
import org.lwjgl.LWJGLUtil;

public class NoctisTest
{
	static class Listener
	{
		@NoctisEvent
		public void mouseMoved(MouseMoveEvent event)
		{
			System.out.println("MouseMoveEvent @ " + event.getPos());
		}
		
		@NoctisEvent
		public void mouseDragged(MouseDraggedEvent event)
		{
			System.out.println("MouseDraggedEvent @ " + event.getPos());
		}
		
		@NoctisEvent
		public void mousePressed(MousePressedEvent event)
		{
			System.out.println("MousePressedEvent @ " + event.getPos());
		}
		
		@NoctisEvent
		public void mouseReleased(MouseReleasedEvent event)
		{
			System.out.println("MouseReleasedEvent @ " + event.getPos());
		}
	}
	
	/**
	 * Starts the game.
	 */
	public static void main(String[] args)
	{
		init();
		
		NFrame frame = new NFrame("Salut");
		
		frame.setWidth(100);
		frame.setHeight(200);
		frame.show();
		
		frame.registerListener(new Listener());
	}

	private static void init()
	{
		try
		{
			LWJGLSetup.load(getBaseWorkingDirectory());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
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
