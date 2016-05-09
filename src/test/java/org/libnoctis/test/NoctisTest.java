package org.libnoctis.test;


import java.io.File;
import java.io.IOException;

import org.libnoctis.theme.NoctisTheme;
import org.libnoctis.theme.ThemeLoadingException;
import org.lwjgl.LWJGLUtil;


public class NoctisTest
{
    /**
     * Starts the game.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        if (!init())
        {
            System.err.println("Couldn't load LWJGL, aborting.");
            return;
        }

//        NFrame frame = new NFrame("Salut");
//        frame.setLayout(new YoloLayout());
//        
//        frame.add(new NTextField());
//
//        frame.setWidth(640);
//        frame.setHeight(480);
//        frame.show();

        NoctisTheme theme = null;
        try
        {
            theme = new NoctisTheme(new File("/home/victor/Ylinor/test/theme.zip"));
        }
        catch (ThemeLoadingException e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("" + theme.prop("unePazeroperty", "Salut"));
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
