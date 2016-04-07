package org.libnoctis.render.gl;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;


public class DirectDrawer extends Drawer
{
	@Override
	public void setColor(Color color)
	{
		glColor4f(color.getRedFloat(), color.getGreenFloat(), color.getBlueFloat(), color.getAlphaFloat());
	}

	@Override
	public void drawRect(int x, int y, int width, int height)
	{
		glBegin(GL_QUADS);
		glVertex2i(x, y);
		glVertex2i(x, y + height);
		glVertex2i(x + width, y + height);
		glVertex2i(x + width, y);
		glEnd();
	}

	@Override
	public boolean paintEveryFrame()
	{
		return true;
	}
}
