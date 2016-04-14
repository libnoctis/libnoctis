package org.libnoctis.test;

import java.awt.image.BufferedImage;

import org.libnoctis.components.NComponent;
import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.NTexture;

public class Button extends NComponent
{
	protected NTexture tex = new NTexture((BufferedImage) null);

	@Override
	protected void init()
	{
		super.init();
		tex = new NTexture((BufferedImage) null);
	}
	
	@Override
	protected void paintComponent(Drawer drawer)
	{
		drawer.setColor(Color.BLUE);
		drawer.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public String toString()
	{
		return "Button [getX()=" + getX() + ", getY()=" + getY() + ", getWidth()=" + getWidth() + ", getHeight()=" + getHeight() + "]";
	}
}
