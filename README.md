# Libnoctis

**Libnoctis is an open-source Swing-like Java GUI library using LWJGL 2.**

## Features

- Layout system (currently only a GridLayout is implemented, you can use absolute positions)
- Font drawing system (with string bouding) with cache
- Nine patch, with both size and general cache
- Zip-based theme with properties
- Automatically generated NinePatch using annotations on components
- Automatic event system using annotations (mouse and keyboard)
- Current implemented components : Button, checkbox, progress bar, text field

## Getting started

Create a NFrame, and add components to it.

```java
public class MyApplication extends NFrame
{
    private NButton button = new NButton("Hey !");
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

        field.setPosition(75, 350);
        field.setSize(500, 50);
        this.add(field);

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
        MyApplication test = new MyApplication("The Coolest Application ever");
        try
        {
            test.loadTheme(new File("mytheme.zip"));
        }
        catch (ThemeLoadingException e)
        {
            e.printStackTrace();
        }
        test.show();
    }
}
```
