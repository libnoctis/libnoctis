package org.libnoctis.layout;

import java.awt.Container;
import java.awt.Insets;
import java.util.List;

import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.util.Dimension;


public class FlowLayout extends NLayout
{
    /**
     * This value indicates that each row of components should be
     * left-justified.
     */
    public static final int LEFT = 0;

    /**
     * This value indicates that each row of components should be centered.
     */
    public static final int CENTER = 1;

    /**
     * This value indicates that each row of components should be
     * right-justified.
     */
    public static final int RIGHT = 2;

    /**
     * This value indicates that each row of components should be justified to
     * the leading edge of the container's orientation, for example, to the left
     * in left-to-right orientations.
     *
     * @see java.awt.Component#getComponentOrientation
     * @see java.awt.ComponentOrientation
     * @since 1.2
     */
    public static final int LEADING = 3;

    /**
     * This value indicates that each row of components should be justified to
     * the trailing edge of the container's orientation, for example, to the
     * right in left-to-right orientations.
     *
     * @see java.awt.Component#getComponentOrientation
     * @see java.awt.ComponentOrientation
     * @since 1.2
     */
    public static final int TRAILING = 4;

    private int newAlign;
    private int hgap;
    private int vgap;
    /**
     * If true, components will be aligned on their baseline.
     */
    private boolean alignOnBaseline;

    /**
     * Constructs a new <code>FlowLayout</code> with a centered alignment and a
     * default 5-unit horizontal and vertical gap.
     */
    public FlowLayout()
    {
        this(CENTER, 5, 5);
    }

    /**
     * Constructs a new <code>FlowLayout</code> with the specified alignment and
     * a default 5-unit horizontal and vertical gap. The value of the alignment
     * argument must be one of <code>FlowLayout.LEFT</code>,
     * <code>FlowLayout.RIGHT</code>, <code>FlowLayout.CENTER</code>,
     * <code>FlowLayout.LEADING</code>, or <code>FlowLayout.TRAILING</code>.
     * 
     * @param align the alignment value
     */
    public FlowLayout(int align)
    {
        this(align, 5, 5);
    }

    /**
     * Creates a new flow layout manager with the indicated alignment and the
     * indicated horizontal and vertical gaps.
     * <p>
     * The value of the alignment argument must be one of
     * <code>FlowLayout.LEFT</code>, <code>FlowLayout.RIGHT</code>,
     * <code>FlowLayout.CENTER</code>, <code>FlowLayout.LEADING</code>, or
     * <code>FlowLayout.TRAILING</code>.
     * 
     * @param align the alignment value
     * @param hgap the horizontal gap between components and between the
     *        components and the borders of the <code>Container</code>
     * @param vgap the vertical gap between components and between the
     *        components and the borders of the <code>Container</code>
     */
    public FlowLayout(int align, int hgap, int vgap)
    {
        this.hgap = hgap;
        this.vgap = vgap;
        setAlignment(align);
    }

    public int getNewAlign()
    {
        return newAlign;
    }

    public void setAlignment(int newAlign)
    {
        this.newAlign = newAlign;
    }

    public int getHgap()
    {
        return hgap;
    }

    public void setHgap(int hgap)
    {
        this.hgap = hgap;
    }

    public int getVgap()
    {
        return vgap;
    }

    public void setVgap(int vgap)
    {
        this.vgap = vgap;
    }

    /**
     * Returns the preferred dimensions for this layout given the <i>visible</i>
     * components in the specified target container.
     *
     * @param target the container that needs to be laid out
     * @return the preferred dimensions to lay out the subcomponents of the
     *         specified container
     * @see Container
     * @see #minimumLayoutSize
     * @see java.awt.Container#getPreferredSize
     */
    public Dimension preferredLayoutSize(NContainer target)
    {
        synchronized (target.getTreeLock())
        {
            Dimension dim = new Dimension(0, 0);
            List<NComponent> children = target.getChildren();
            boolean firstVisibleComponent = true;
            boolean useBaseline = alignOnBaseline;
            int maxAscent = 0;
            int maxDescent = 0;

            for (int i = 0; i < children.size(); i++)
            {
                NComponent child = children.get(i);
                if (child.isVisible())
                {
                    Dimension childDimension = child.getPreferredSize();
                    dim.height = Math.max(dim.height, childDimension.height);
                    if (firstVisibleComponent)
                    {
                        firstVisibleComponent = false;
                    }
                    else
                    {
                        dim.width += hgap;
                    }
                    dim.width += childDimension.width;
                    if (useBaseline)
                    {
                        int baseline = child.getBaseline(childDimension.width, childDimension.height);
                        if (baseline >= 0)
                        {
                            maxAscent = Math.max(maxAscent, baseline);
                            maxDescent = Math.max(maxDescent, childDimension.height - baseline);
                        }
                    }
                }
            }
            if (useBaseline)
            {
                dim.height = Math.max(maxAscent + maxDescent, dim.height);
            }
            Insets insets = target.getInsets();
            dim.width += insets.left + insets.right + hgap * 2;
            dim.height += insets.top + insets.bottom + vgap * 2;
            return dim;
        }
    }

    public Dimension minimumLayoutSize(NContainer target)
    {
        synchronized (target.getTreeLock())
        {
            Dimension dim = new Dimension(0, 0);
            List<NComponent> children = target.getChildren();
            boolean firstVisibleComponent = true;
            boolean useBaseline = alignOnBaseline;
            int maxAscent = 0;
            int maxDescent = 0;

            for (int i = 0; i < children.size(); i++)
            {
                NComponent child = children.get(i);
                if (child.isVisible())
                {
                    Dimension childDimension = child.getMinimumSize();
                    dim.height = Math.max(dim.height, childDimension.height);
                    if (firstVisibleComponent)
                    {
                        firstVisibleComponent = false;
                    }
                    else
                    {
                        dim.width += hgap;
                    }
                    dim.width += childDimension.width;
                    if (useBaseline)
                    {
                        int baseline = child.getBaseline(childDimension.width, childDimension.height);
                        if (baseline >= 0)
                        {
                            maxAscent = Math.max(maxAscent, baseline);
                            maxDescent = Math.max(maxDescent, childDimension.height - baseline);
                        }
                    }
                }
            }
            if (useBaseline)
            {
                dim.height = Math.max(maxAscent + maxDescent, dim.height);
            }
            Insets insets = target.getInsets();
            dim.width += insets.left + insets.right + hgap * 2;
            dim.height += insets.top + insets.bottom + vgap * 2;
            return dim;
        }
    }

    /**
     * Centers the elements in the specified row, if there is any slack.
     * 
     * @param target the component which needs to be moved
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width dimensions
     * @param height the height dimensions
     * @param rowStart the beginning of the row
     * @param rowEnd the the ending of the row
     * @param useBaseline Whether or not to align on baseline.
     * @param ascent Ascent for the components. This is only valid if
     *        useBaseline is true.
     * @param descent Ascent for the components. This is only valid if
     *        useBaseline is true.
     * @return actual row height
     */
    private int moveComponents(NContainer target, int x, int y, int width, int height, int rowStart, int rowEnd, boolean ltr, boolean useBaseline, int[] ascent, int[] descent)
    {
        switch (newAlign)
        {
            case LEFT:
                x += ltr ? 0 : width;
                break;
            case CENTER:
                x += width / 2;
                break;
            case RIGHT:
                x += ltr ? width : 0;
                break;
            case LEADING:
                break;
            case TRAILING:
                x += width;
                break;
        }
        int maxAscent = 0;
        int nonbaselineHeight = 0;
        int baselineOffset = 0;
        if (useBaseline)
        {
            int maxDescent = 0;
            for (int i = rowStart; i < rowEnd; i++)
            {
                NComponent m = target.getComponent(i);
                if (m.isVisible())
                {
                    if (ascent[i] >= 0)
                    {
                        maxAscent = Math.max(maxAscent, ascent[i]);
                        maxDescent = Math.max(maxDescent, descent[i]);
                    }
                    else
                    {
                        nonbaselineHeight = Math.max(m.getHeight(), nonbaselineHeight);
                    }
                }
            }
            height = Math.max(maxAscent + maxDescent, nonbaselineHeight);
            baselineOffset = (height - maxAscent - maxDescent) / 2;
        }
        for (int i = rowStart; i < rowEnd; i++)
        {
            NComponent m = target.getComponent(i);
            if (m.isVisible())
            {
                int cy;
                if (useBaseline && ascent[i] >= 0)
                {
                    cy = y + baselineOffset + maxAscent - ascent[i];
                }
                else
                {
                    cy = y + (height - m.getHeight()) / 2;
                }
                if (ltr)
                {
                    m.setPosition(x, cy);
                }
                else
                {
                    m.setPosition(target.getWidth() - x - m.getWidth(), cy);
                }
                x += m.getWidth() + hgap;
            }
        }
        return height;
    }

    @Override
    public void layoutContainer(NContainer target)
    {
        synchronized (target.getTreeLock())
        {
            System.out.println("layout");
            Insets insets = target.getInsets();
            int maxwidth = target.getWidth() - (insets.left + insets.right + hgap * 2);
            int nmembers = target.getComponentCount();
            int x = 0, y = insets.top + vgap;
            int rowh = 0, start = 0;

            boolean ltr = false;

            boolean useBaseline = alignOnBaseline;
            int[] ascent = null;
            int[] descent = null;

            if (useBaseline)
            {
                ascent = new int[nmembers];
                descent = new int[nmembers];
            }

            for (int i = 0; i < nmembers; i++)
            {
                NComponent m = target.getComponent(i);
                if (m.isVisible())
                {
                    Dimension d = m.getPreferredSize();
                    m.setSize(d.width, d.height);

                    if (useBaseline)
                    {
                        int baseline = m.getBaseline(d.width, d.height);
                        if (baseline >= 0)
                        {
                            ascent[i] = baseline;
                            descent[i] = d.height - baseline;
                        }
                        else
                        {
                            ascent[i] = -1;
                        }
                    }
                    if ((x == 0) || ((x + d.width) <= maxwidth))
                    {
                        if (x > 0)
                        {
                            x += hgap;
                        }
                        x += d.width;
                        rowh = Math.max(rowh, d.height);
                    }
                    else
                    {
                        rowh = moveComponents(target, insets.left + hgap, y, maxwidth - x, rowh, start, i, ltr, useBaseline, ascent, descent);
                        x = d.width;
                        y += vgap + rowh;
                        rowh = d.height;
                        start = i;
                    }
                }
            }
            moveComponents(target, insets.left + hgap, y, maxwidth - x, rowh, start, nmembers, ltr, useBaseline, ascent, descent);
        }
    }

    @Override
    boolean areValidConstraints(Object constraints, NComponent component)
    {
        // We don't care.
        return true;
    }
}
