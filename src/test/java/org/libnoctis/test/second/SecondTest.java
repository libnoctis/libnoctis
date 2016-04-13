package org.libnoctis.test.second;

import java.io.File;
import java.io.IOException;
import org.libnoctis.components.NFrame;
import org.libnoctis.components.base.NButton;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.layout.base.YoloLayout;
import org.libnoctis.test.LWJGLSetup;

public class SecondTest extends NFrame
{
    private NButton button = new NButton("Yolo !");

    public SecondTest(String title)
    {
        super(title);

        this.setWidth(750);
        this.setHeight(750);
        this.setUndecorated(true);
        this.setLayout(new YoloLayout());

        this.add(button);
    }

    @NoctisEvent
    public void monEventMousePressed(MousePressedEvent event)
    {
        System.out.println("Et c'est le click =)");
    }

    public static void main(String[] args) throws IOException
    {
        LWJGLSetup.load(new File(System.getProperty("user.home"), ".noctis"));

        SecondTest test = new SecondTest("Second test");
        test.show();
    }
}
