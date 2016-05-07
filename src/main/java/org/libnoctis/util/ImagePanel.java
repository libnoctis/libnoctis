package org.libnoctis.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * panel to show an image 
 * @author  hakan eryargi (r a f t)
 */
public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	final Image image;
	final Rectangle2D bounds;
    
    /** Creates a new instance of ShowSkin */
    public ImagePanel(Image image, Rectangle2D bounds) {
        this.image = image;
        this.bounds = bounds;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
    }
    
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.drawImage(image, 0, 0, null);
        g.setColor(Color.RED);
        g.drawRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
    }
}
