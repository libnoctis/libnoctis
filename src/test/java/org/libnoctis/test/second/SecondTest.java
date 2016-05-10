package org.libnoctis.test.second;

import java.io.File;
import java.io.IOException;

import org.libnoctis.components.NFrame;
import org.libnoctis.components.base.NButton;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.layout.base.AbsoluteLayout;
import org.libnoctis.layout.base.AbsoluteLayoutProperty;
import org.libnoctis.test.LWJGLSetup;
import org.libnoctis.theme.ThemeLoadingException;

public class SecondTest extends NFrame implements NListener
{
    private NButton button = new NButton("Yolo !");

    public SecondTest(String title)
    {
        super(title);

        this.setWidth(750);
        this.setHeight(750);
        this.setLayout(new AbsoluteLayout());

        button.setPosition(new AbsoluteLayoutProperty(75, 75));
        button.setWidth(500);
        button.setHeight(50);

        this.add(button);
        this.registerListener(this);
    }

    @NoctisEvent
    public void monEventMousePressed(MousePressedEvent event)
    {
        System.out.println("Et c'est le click =)");
    }

    public static void main(String[] args) throws IOException
    {
        LWJGLSetup.load(new File(System.getProperty("user.home"), ".lwjgl"));

        SecondTest test = new SecondTest("Second test");
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
}
