package org.libnoctis.render;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;

import org.libnoctis.components.NComponent;

public class DisplayListDrawer extends DirectDrawer
{
	@Override
	public void render(NComponent component)
	{
		glCallList(component.displayList);
	}
	
	@Override
	public void prePaint(NComponent component)
	{
		if (component.displayList != -1)
		{
			glDeleteLists(component.displayList, 1);
		}

		component.displayList = glGenLists(1);
		
		glNewList(component.displayList, GL_COMPILE);
	}

	@Override
	public void postPaint(NComponent component)
	{
		glEndList();
	}
	
	@Override
	public boolean paintEveryFrame()
	{
		return false;
	}
}
