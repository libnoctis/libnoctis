package org.libnoctis.test;


import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.libnoctis.components.NFrame;
import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.FontCache;
import org.libnoctis.render.gl.GlFont;
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

            GlFont font;

            @Override
            protected void paintComponent(Drawer drawer)
            {
                if (font == null)
                {
                    font = FontCache.getGlFont(new Font("URW Bookman L", Font.PLAIN, 40));
                    drawer.setFont(font);
                }

                drawer.drawRect(0, 0, 100, 100);
                drawer.setColor(Color.RED);
                drawer.drawString("Coucou lol", 0, 0);

                drawer.setColor(Color.WHITE);
                drawer.drawString("Coucou lol", 0, 100);
            }
        };

        frame.setWidth(640);
        frame.setHeight(480);
        frame.show();
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
