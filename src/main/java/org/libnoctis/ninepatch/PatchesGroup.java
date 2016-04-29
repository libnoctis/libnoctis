package org.libnoctis.ninepatch;

import org.libnoctis.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class PatchesGroup
{
    private int[] pixels;

    private List<Vector2i> fixed =  new ArrayList<Vector2i>();
    private List<Vector2i> patches = new ArrayList<Vector2i>();

    private boolean startsWithPatch;

    public PatchesGroup(int[] pixels)
    {
        this.pixels = pixels;
    }

    public void read()
    {
        int lastIndex = 0;
        int lastPixel = pixels[0];
        boolean first = true;

        for (int i = 0; i < pixels.length; i++)
        {
            int pixel = pixels[i];
            if (pixel != lastPixel)
            {
                if (lastPixel == 0xFF000000)
                {
                    if (first)
                        startsWithPatch = true;

                    patches.add(new Vector2i(lastIndex, i));
                }
                else
                    fixed.add(new Vector2i(lastIndex, i));

                first = false;
                lastIndex = i;
                lastPixel = pixel;
            }
        }
        if (lastPixel == 0xFF000000)
        {
            if (first)
                startsWithPatch = true;

            patches.add(new Vector2i(lastIndex, pixels.length));
        }
        else
            fixed.add(new Vector2i(lastIndex, pixels.length));

        if (patches.size() == 0)
        {
            patches.add(new Vector2i(1, pixels.length));
            startsWithPatch = true;
            fixed.clear();
        }
    }

    public void setPixels(int[] pixels)
    {
        this.pixels = pixels;
    }

    public List<Vector2i> getFixed()
    {
        return fixed;
    }

    public List<Vector2i> getPatches()
    {
        return patches;
    }

    public boolean doesStartWithPatch()
    {
        return startsWithPatch;
    }
}
