package org.libnoctis.test.second;

import java.io.File;
import java.io.IOException;
import org.libnoctis.components.NFrame;
import org.libnoctis.components.base.NButton;
import org.libnoctis.components.base.NCheckbox;
import org.libnoctis.components.base.NProgressBar;
import org.libnoctis.components.base.NTextField;
import org.libnoctis.test.LWJGLSetup;
import org.libnoctis.theme.ThemeLoadingException;

public class SecondTest extends NFrame
{
    private NButton button = new NButton("Yolo !");
    private NButton button2 = new NButton("MDR !");
    private NButton button1 = new NButton("LOL !");

    private NTextField field = new NTextField();

    private NCheckbox box = new NCheckbox();

    private NProgressBar bar = new NProgressBar();

    public SecondTest(String title)
    {
        super(title);

        this.setSize(750, 750);

        button.setPosition(75, 50);
        button.setSize(450, 50);
        this.add(button);

        button1.setPosition(75, 150);
        button1.setSize(550, 150);
        this.add(button1);

        field.setPosition(75, 350);
        field.setSize(500, 50);
        this.add(field);

        button2.setPosition(75, 500);
        button2.setSize(650, 50);
        this.add(button2);

        box.setPosition(5, 575);
        box.setSize(50, 50);
        this.add(box);

        bar.setValue(50);
        bar.setMaximum(100);
        bar.setPosition(5, 650);
        bar.setSize(500, 50);
        this.add(bar);
    }

    public static void main(String[] args) throws IOException
    {
        LWJGLSetup.load(new File(System.getProperty("user.home"), ".lwjgl"));

        SecondTest test = new SecondTest("Second test");
        try
        {
            test.loadTheme(new File("/home/litarvan/Documents/Ylinor/Design/noctis/ylinor.zip"));
        }
        catch (ThemeLoadingException e)
        {
            e.printStackTrace();
        }
        test.show();
    }
}
