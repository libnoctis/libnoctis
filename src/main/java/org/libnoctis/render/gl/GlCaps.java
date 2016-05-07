package org.libnoctis.render.gl;

import static org.lwjgl.opengl.GL11.GL_MAX_TEXTURE_SIZE;
import static org.lwjgl.opengl.GL11.glGetInteger;

public class GlCaps
{
    private static int MAX_TEXTURE_SIZE = -1;
    
    public static int getMaxTextureSize()
    {
        if (MAX_TEXTURE_SIZE == -1)
        {
            MAX_TEXTURE_SIZE = glGetInteger(GL_MAX_TEXTURE_SIZE);
        }
        
        return MAX_TEXTURE_SIZE;
    }
}
