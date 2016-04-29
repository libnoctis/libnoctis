package org.libnoctis.ninepatch;

public class DrawingData
{
    private int   horizontalRemainder;
    private int   verticalRemainder;
    private float horizontalPatchesSum;
    private float verticalPatchesSum;

    public DrawingData()
    {
    }

    public DrawingData(int horizontalRemainder, int verticalRemainder, float horizontalPatchesSum, float verticalPatchesSum)
    {
        this.horizontalRemainder = horizontalRemainder;
        this.verticalRemainder = verticalRemainder;
        this.horizontalPatchesSum = horizontalPatchesSum;
        this.verticalPatchesSum = verticalPatchesSum;
    }

    public int getHorizontalRemainder()
    {
        return horizontalRemainder;
    }

    void setHorizontalRemainder(int horizontalRemainder)
    {
        this.horizontalRemainder = horizontalRemainder;
    }

    public int getVerticalRemainder()
    {
        return verticalRemainder;
    }

    void setVerticalRemainder(int verticalRemainder)
    {
        this.verticalRemainder = verticalRemainder;
    }

    public float getHorizontalPatchesSum()
    {
        return horizontalPatchesSum;
    }

    void setHorizontalPatchesSum(float horizontalPatchesSum)
    {
        this.horizontalPatchesSum = horizontalPatchesSum;
    }

    public float getVerticalPatchesSum()
    {
        return verticalPatchesSum;
    }

    void setVerticalPatchesSum(float verticalPatchesSum)
    {
        this.verticalPatchesSum = verticalPatchesSum;
    }
}