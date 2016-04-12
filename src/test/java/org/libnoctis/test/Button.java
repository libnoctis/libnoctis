package org.libnoctis.test;


import java.awt.image.BufferedImage;

import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;


public class Button extends NComponent
{
	protected GlTexture tex;
	
	public Button()
	{
	
	}
	
	@Override
	protected void onComponentAdded(NContainer parent)
	{
		super.onComponentAdded(parent);
		setX(0);
		setY(0);
		setWidth(200);
		setHeight(100);
	}

	@Override
	protected void init()
	{
		super.init();
		tex = new GlTexture((BufferedImage) null);
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
