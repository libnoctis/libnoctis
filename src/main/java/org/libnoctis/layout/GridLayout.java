package org.libnoctis.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.util.Dimension;


public class GridLayout extends NLayout
{
    /**
     * Amount of rows in this grid.
     */
    private int rows;

    /**
     * Amount of columns in this grid.
     */
    private int columns;

    /**
     * Distance between two rows, in pixels.
     */
    private int vgap;

    /**
     * Distance between two columns, in pixels.
     */
    private int hgap;

    private transient List<GridLayoutConstraints> freeCells;

    /**
     * Creates a new single cell {@link GridLayout}.
     */
    public GridLayout()
    {
        this(1, 1);
    }

    /**
     * Creates a new {@link GridLayout}.
     * 
     * @param rows The amount of rows in the grid.
     * @param columns The amount of columns in the grid.
     * @see GridLayout#GridLayout(int, int, int, int)
     */
    public GridLayout(int rows, int columns)
    {
        this(rows, columns, 0, 0);
    }

    /**
     * Creates a new {@link GridLayout}.
     * 
     * @param rows The amount of rows in the grid.
     * @param columns The amount of columns in the grid.
     * @param vgap The distance in pixels between two rows.
     * @param hgap The distance in pixels between two columns.
     */
    public GridLayout(int rows, int columns, int vgap, int hgap)
    {
        if ((rows == 0) && (columns == 0))
        {
            throw new IllegalArgumentException("Rows and columns cannot both be zero");
        }

        this.rows = rows;
        this.columns = columns;
        this.vgap = vgap;
        this.hgap = hgap;
        this.freeCells = new ArrayList<GridLayoutConstraints>();
    }

    /**
     * Gets the amount of rows.
     * 
     * @return The amount of rows in this grid.
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * Gets the amount of columns.
     * 
     * @return The amount of columns in this grid.
     */
    public int getColumns()
    {
        return columns;
    }

    /**
     * Gets the vertical gap, distance in pixels between two rows.
     * 
     * @return The vertical gap, distance in pixels between two rows.
     */
    public int getVgap()
    {
        return vgap;
    }

    /**
     * Gets the horizontal gap, distance in pixels between two columns.
     * 
     * @return The horizontal gap, distance in pixels between two columns.
     */
    public int getHgap()
    {
        return hgap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void layoutContainer(NContainer parent)
    {
        List<NComponent> children = new ArrayList<NComponent>(parent.getChildren());

        // Check if there is enough space.
        if (children.size() > rows * columns)
        {
            throw new IllegalArgumentException("Too many children (" + children.size() + ") for a " + rows + "x" + columns + " grid.");
        }
        
        if (!minimumLayoutSize(parent).fit(parent.getWidth(), parent.getHeight()))
        {
            return;
        }

        // Compute elements that has constraints first
        Collections.sort(children, new Comparator<NComponent>() {
            @Override
            public int compare(NComponent o1, NComponent o2)
            {
                if (o1.getLayoutConstraints() == null && o2.getLayoutConstraints() == null)
                {
                    return 0;
                }
                else if (o1.getLayoutConstraints() == null)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        });

        // Recalculate free cells
        fillFreeCells();
        
        /*
         * A cell size in pixels.
         */
        Dimension cellSize = new Dimension((parent.getWidth() - (columns - 1) * hgap - parent.getInsets().left - parent.getInsets().right) / columns, (parent.getHeight() - (rows - 1) * vgap - parent.getInsets().top - parent.getInsets().bottom) / rows);

        // Used for 
        Dimension temp = new Dimension();

        for (int i = 0; i < children.size(); i++)
        {
            NComponent child = children.get(i);
            GridLayoutConstraints constraints = (GridLayoutConstraints) child.getLayoutConstraints();

            // If this component has no constraints, it goes in the first empty cell
            if (constraints == null)
            {
                constraints = freeCells.get(0);
            }

            // Apply preferred size
            Dimension.min(cellSize, child.getPreferredSize(), temp);

            // Set pos and size
            child.setX(parent.getInsets().left + constraints.getX() * (cellSize.getWidth() + hgap));
            child.setY(parent.getInsets().top + constraints.getY() * (cellSize.getHeight() + vgap));
            child.setWidth(temp.getWidth());
            child.setHeight(temp.getHeight());

            // This cell is not longer empty
            freeCells.remove(constraints);
        }
    }

    /**
     * Puts into {@link GridLayout#freeCells} every grid position.
     */
    private void fillFreeCells()
    {
        freeCells.clear();

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                freeCells.add(new GridLayoutConstraints(i, j));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean areValidConstraints(Object properties, NComponent component)
    {
        return properties instanceof GridLayoutConstraints;
    }
}
