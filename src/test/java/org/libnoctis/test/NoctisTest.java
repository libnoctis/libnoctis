package org.libnoctis.test;

import java.io.File;
import java.io.IOException;

import org.libnoctis.components.NFrame;
import org.libnoctis.components.base.NButton;
import org.libnoctis.components.base.NTextField;
import org.libnoctis.input.NListener;
import org.libnoctis.layout.FlowLayout;
import org.libnoctis.theme.ThemeLoadingException;
import org.lwjgl.LWJGLUtil;


public class NoctisTest extends NFrame implements NListener
{
    public NoctisTest(String title)
    {
        super(title);
        setResizable(true);
        this.setWidth(750);
        this.setHeight(750);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        NTextField field = new NTextField();

        add(field);
        for (int i = 0; i < 20; i++)
            add(new NButton("Button §(aaacfeff)azeaze"));

        this.registerListener(this);
    }

    /**
     * Starts the game.
     * 
     * @throws IOException
     * @throws ThemeLoadingException
     */
    public static void main(String[] args) throws IOException, ThemeLoadingException
    {
        if (!initLwjgl())
        {
            System.err.println("Couldn't load LWJGL, aborting.");
            return;
        }

        NoctisTest test = new NoctisTest("First test");
        try
        {
            test.loadTheme(new File("/home/victor/Ylinor/test/ylinor.zip"));
        }
        catch (ThemeLoadingException e)
        {
            e.printStackTrace();
        }
        test.show();
    }

    private static boolean initLwjgl()
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
