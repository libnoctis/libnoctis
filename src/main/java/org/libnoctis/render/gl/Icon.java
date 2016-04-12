package org.libnoctis.render.gl;


public class Icon
{
	public static final Icon WHOLE_TEXTURE = new Icon(0.0f, 0.0f, 1.0f, 1.0f);
	
	/**
	 * The top-left corner texture X coordinate (0.0 - 1.0).
	 */
	private final float minU;

	/**
	 * The top-left corner texture Y coordinate (0.0 - 1.0).
	 */
	private final float minV;

	/**
	 * The bottom-right corner texture X coordinate (0.0 - 1.0).
	 */
	private final float maxU;

	/**
	 * The bottom-right corner texture Y coordinate (0.0 - 1.0).
	 */
	private final float maxV;

	/**
	 * A piece of a texture.
	 * 
	 * @param minU The top-left corner texture X coordinate (0.0 - 1.0).
	 * @param minV The top-left corner texture Y coordinate (0.0 - 1.0).
	 * @param maxU The bottom-right corner texture X coordinate (0.0 - 1.0).
	 * @param maxV The bottom-right corner texture Y coordinate (0.0 - 1.0).
	 */
	public Icon(float minU, float minV, float maxU, float maxV)
	{
		this.minU = minU;
		this.minV = minV;
		this.maxU = maxU;
		this.maxV = maxV;
	}

	public float getMinU()
	{
		return minU;
	}

	public float getMinV()
	{
		return minV;
	}

	public float getMaxU()
	{
		return maxU;
	}

	public float getMaxV()
	{
		return maxV;
	}
}
