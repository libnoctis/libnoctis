package org.libnoctis.layout;

public class GridLayoutConstraints extends LayoutConstraints
{
    /**
     * The X position in the grid, or column index.
     */
    private final int x;
    
    /**
     * The Y position in the grid, or row index.
     */
    private final int y;

    /**
     * Creates a new {@link GridLayoutConstraints}.
     * 
     * @param x X position in the grid (column index).
     * @param y Y position in the grid (row index).
     */
    public GridLayoutConstraints(int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X position in the grid, or column index.
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The Y position in the grid, or row index.
     */
    public int getY()
    {
        return y;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GridLayoutConstraints other = (GridLayoutConstraints) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
