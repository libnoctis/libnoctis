package org.libnoctis.util;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author hakan eryargi (r a f t)
 */
public class SwingUtil {

	/** wraps components in a JFrame and shows it */
	public static JFrame showComponent(JComponent component)  {
		return showComponent(component.getClass().getName(), component);
	}
	
	/** wraps components in a JFrame and shows it */
	public static JFrame showComponent(String title, JComponent component)  {
		JFrame frame = new JFrame(title);
		frame.add(component);
		frame.pack();
		frame.setVisible(true);
		return frame;
	}
	
	/** wraps components in a JFrame and shows it */
	public static JFrame showImage(Image image, Rectangle2D bounds)  {
    	String title = "image: " + image.getWidth(null) + "x" + image.getHeight(null);
		return showComponent(title, new ImagePanel(image, bounds));
	}
}
