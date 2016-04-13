package org.libnoctis.layout.base;

import org.libnoctis.components.NComponent;
import org.libnoctis.layout.NLayout;
import org.libnoctis.util.Vector2i;

public class YoloLayout extends NLayout
{
    @Override
    public Vector2i[] getElementsPosition(NComponent[] components)
    {
        Vector2i[] vectors = new Vector2i[components.length];
        for (int i = 0; i < vectors.length; i++)
            vectors[i] = new Vector2i(50, 50);

        return vectors;
    }
}
