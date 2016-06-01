package org.libnoctis.layout;

import java.awt.Insets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.util.Dimension;


public class BorderLayout extends NLayout
{
    public static final String CENTER = "Center";
    public static final String WEST = "West";
    public static final String EAST = "East";
    public static final String NORTH = "North";
    public static final String SOUTH = "South";
    private static final List<String> VALID_CONSTRAINTS = Arrays.asList(CENTER, WEST, EAST, NORTH, SOUTH);

    /**
     * Distance between two rows, in pixels.
     */
    private int vgap;

    /**
     * Distance between two columns, in pixels.
     */
    private int hgap;

    @Override
    public void layoutContainer(NContainer parent)
    {
        if (!minimumLayoutSize(parent).fit(parent.getWidth(), parent.getHeight()))
        {
            return;
        }
        
        synchronized (parent.getTreeLock())
        {
            List<NComponent> children = parent.getChildren();

            if (children.size() > 5)
            {
                throw new IllegalArgumentException("A BorderLayout cannot hold properly more than 5 components.");
            }

            HashMap<String, NComponent> constraintsToComponent = new HashMap<String, NComponent>();

            for (int i = 0; i < children.size(); i++)
            {
                constraintsToComponent.put((String) children.get(i).getLayoutConstraints(), children.get(i));
            }

            if (constraintsToComponent.size() < children.size())
            {
                throw new IllegalArgumentException("Can not set twice the same constraints in a BorderLayout.");
            }

            Insets insets = parent.getInsets();
            int top = insets.top;
            int bottom = parent.getHeight() - insets.bottom;
            int left = insets.left;
            int right = parent.getWidth() - insets.right;

            NComponent comp = null;

            if ((comp = constraintsToComponent.get(NORTH)) != null)
            {
                comp.setSize(right - left, comp.getHeight());
                Dimension d = comp.getPreferredSize();
                comp.setBounds(left, top, right - left, d.height);
                top += d.width + vgap;
            }
            if ((comp = constraintsToComponent.get(SOUTH)) != null)
            {
                comp.setSize(right - left, comp.getHeight());
                Dimension d = comp.getPreferredSize();
                comp.setBounds(left, bottom - d.height, right - left, d.height);
                bottom -= d.height + vgap;
            }
            if ((comp = constraintsToComponent.get(EAST)) != null)
            {
                comp.setSize(comp.getWidth(), bottom - top);
                Dimension d = comp.getPreferredSize();
                comp.setBounds(right - d.width, top, d.width, bottom - top);
                right -= d.width + hgap;
            }
            if ((comp = constraintsToComponent.get(WEST)) != null)
            {
                comp.setSize(comp.getWidth(), bottom - top);
                Dimension d = comp.getPreferredSize();
                comp.setBounds(left, top, d.width, bottom - top);
                left += d.width + hgap;
            }
            if ((comp = constraintsToComponent.get(CENTER)) != null)
            {
                comp.setBounds(left, top, right - left, bottom - top);
            }
        }
    }

    @Override
    public Dimension preferredLayoutSize(NContainer parent)
    {
        synchronized (parent.getTreeLock())
        {
            Dimension dim = new Dimension();

            List<NComponent> children = parent.getChildren();

            if (children.size() > 5)
            {
                throw new IllegalArgumentException("A BorderLayout cannot hold properly more than 5 components.");
            }

            HashMap<String, NComponent> constraintsToComponent = new HashMap<String, NComponent>();

            for (int i = 0; i < children.size(); i++)
            {
                constraintsToComponent.put((String) children.get(i).getLayoutConstraints(), children.get(i));
            }

            if (constraintsToComponent.size() < children.size())
            {
                throw new IllegalArgumentException("Can not set twice the same constraints in a BorderLayout.");
            }

            NComponent comp = null;

            if ((comp = constraintsToComponent.get(EAST)) != null)
            {
                Dimension d = comp.getPreferredSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            if ((comp = constraintsToComponent.get(WEST)) != null)
            {
                Dimension d = comp.getPreferredSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            if ((comp = constraintsToComponent.get(CENTER)) != null)
            {
                Dimension d = comp.getPreferredSize();
                dim.width += d.width;
                dim.height = Math.max(d.height, dim.height);
            }
            if ((comp = constraintsToComponent.get(NORTH)) != null)
            {
                Dimension d = comp.getPreferredSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }
            if ((comp = constraintsToComponent.get(SOUTH)) != null)
            {
                Dimension d = comp.getPreferredSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }

            Insets insets = parent.getInsets();
            dim.width += insets.left + insets.right;
            dim.height += insets.top + insets.bottom;
            return dim;
        }
    }

    @Override
    public Dimension minimumLayoutSize(NContainer parent)
    {
        synchronized (parent.getTreeLock())
        {
            Dimension dim = new Dimension();

            List<NComponent> children = parent.getChildren();

            if (children.size() > 5)
            {
                throw new IllegalArgumentException("A BorderLayout cannot hold properly more than 5 components.");
            }

            HashMap<String, NComponent> constraintsToComponent = new HashMap<String, NComponent>();

            for (int i = 0; i < children.size(); i++)
            {
                constraintsToComponent.put((String) children.get(i).getLayoutConstraints(), children.get(i));
            }

            if (constraintsToComponent.size() < children.size())
            {
                throw new IllegalArgumentException("Can not set twice the same constraints in a BorderLayout.");
            }

            NComponent comp = null;

            if ((comp = constraintsToComponent.get(EAST)) != null)
            {
                Dimension d = comp.getMinimumSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            if ((comp = constraintsToComponent.get(WEST)) != null)
            {
                Dimension d = comp.getMinimumSize();
                dim.width += d.width + hgap;
                dim.height = Math.max(d.height, dim.height);
            }
            if ((comp = constraintsToComponent.get(CENTER)) != null)
            {
                Dimension d = comp.getMinimumSize();
                dim.width += d.width;
                dim.height = Math.max(d.height, dim.height);
            }
            if ((comp = constraintsToComponent.get(NORTH)) != null)
            {
                Dimension d = comp.getMinimumSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }
            if ((comp = constraintsToComponent.get(SOUTH)) != null)
            {
                Dimension d = comp.getMinimumSize();
                dim.width = Math.max(d.width, dim.width);
                dim.height += d.height + vgap;
            }

            Insets insets = parent.getInsets();
            dim.width += insets.left + insets.right;
            dim.height += insets.top + insets.bottom;
            return dim;
        }
    }

    @Override
    boolean areValidConstraints(Object constraints, NComponent component)
    {
        return constraints instanceof String && VALID_CONSTRAINTS.contains((String) constraints);
    }
}
