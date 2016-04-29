package org.libnoctis.ninepatch;

import org.libnoctis.util.Vector2i;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class NoctisNinePatchChunk
{
    private List<Rectangle> fixed;
    private List<Rectangle> patches;

    private List<Rectangle> horizontalPatches = new ArrayList<Rectangle>();
    private List<Rectangle> verticalPatches   = new ArrayList<Rectangle>();

    private Vector2i horizontalPadding;
    private Vector2i verticalPadding;

    private boolean horizontalStartsWithPatch;
    private boolean verticalStartsWithPatch;

    public NoctisNinePatchChunk()
    {
    }

    public NoctisNinePatchChunk(List<Rectangle> fixed, List<Rectangle> patches, List<Rectangle> horizontalPatches, List<Rectangle> verticalPatches, Vector2i horizontalPadding, Vector2i verticalPadding, boolean horizontalStartsWithPatch, boolean verticalStartsWithPatch)
    {
        this.fixed = fixed;
        this.patches = patches;
        this.horizontalPatches = horizontalPatches;
        this.verticalPatches = verticalPatches;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.horizontalStartsWithPatch = horizontalStartsWithPatch;
        this.verticalStartsWithPatch = verticalStartsWithPatch;
    }

    public List<Rectangle> getFixed()
    {
        return fixed;
    }

    public void setFixed(List<Rectangle> fixed)
    {
        this.fixed = fixed;
    }

    public List<Rectangle> getPatches()
    {
        return patches;
    }

    public void setPatches(List<Rectangle> patches)
    {
        this.patches = patches;
    }

    public List<Rectangle> getHorizontalPatches()
    {
        return horizontalPatches;
    }

    public void setHorizontalPatches(List<Rectangle> horizontalPatches)
    {
        this.horizontalPatches = horizontalPatches;
    }

    public List<Rectangle> getVerticalPatches()
    {
        return verticalPatches;
    }

    public void setVerticalPatches(List<Rectangle> verticalPatches)
    {
        this.verticalPatches = verticalPatches;
    }

    public Vector2i getHorizontalPadding()
    {
        return horizontalPadding;
    }

    public void setHorizontalPadding(Vector2i horizontalPadding)
    {
        this.horizontalPadding = horizontalPadding;
    }

    public Vector2i getVerticalPadding()
    {
        return verticalPadding;
    }

    public void setVerticalPadding(Vector2i verticalPadding)
    {
        this.verticalPadding = verticalPadding;
    }

    public boolean isHorizontalStartsWithPatch()
    {
        return horizontalStartsWithPatch;
    }

    public void setHorizontalStartsWithPatch(boolean horizontalStartsWithPatch)
    {
        this.horizontalStartsWithPatch = horizontalStartsWithPatch;
    }

    public boolean isVerticalStartsWithPatch()
    {
        return verticalStartsWithPatch;
    }

    public void setVerticalStartsWithPatch(boolean verticalStartsWithPatch)
    {
        this.verticalStartsWithPatch = verticalStartsWithPatch;
    }
}
