package org.libnoctis.test.second;

import java.io.File;
import java.io.IOException;
import org.libnoctis.components.NFrame;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.render.Drawer;
import org.libnoctis.test.LWJGLSetup;

public class SecondTest extends NFrame
{
    public SecondTest(String title)
    {
        super(title);

        this.setWidth(750);
        this.setHeight(750);
        //this.setUndecorated(true);

        this.registerListener(this);
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        drawer.drawRect(50, 50, 50, 50);
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
