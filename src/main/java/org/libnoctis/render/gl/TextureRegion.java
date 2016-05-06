package org.libnoctis.render.gl;


public class TextureRegion
{
    /**
     * Represents the whole texture.
     */
    public static final TextureRegion WHOLE_TEXTURE = new TextureRegion(0.0f, 0.0f, 1.0f, 1.0f);

    /**
     * The top-left corner texture X coordinate (0.0 - 1.0).
     */
    private float minU;

    /**
     * The top-left corner texture Y coordinate (0.0 - 1.0).
     */
    private float minV;

    /**
     * The bottom-right corner texture X coordinate (0.0 - 1.0).
     */
    private float maxU;

    /**
     * The bottom-right corner texture Y coordinate (0.0 - 1.0).
     */
    private float maxV;

    /**
     * A piece of a texture.
     * 
     * @param minU The top-left corner texture X coordinate (0.0 - 1.0).
     * @param minV The top-left corner texture Y coordinate (0.0 - 1.0).
     * @param maxU The bottom-right corner texture X coordinate (0.0 - 1.0).
     * @param maxV The bottom-right corner texture Y coordinate (0.0 - 1.0).
     */
    public TextureRegion(float minU, float minV, float maxU, float maxV)
    {
        this.minU = minU;
        this.minV = minV;
        this.maxU = maxU;
        this.maxV = maxV;
    }

    /**
     * @return The top-left corner texture X coordinate (0.0 - 1.0).
     */
    public float getMinU()
    {
        return minU;
    }

    /**
     * @return The top-left corner texture Y coordinate (0.0 - 1.0).
     */
    public float getMinV()
    {
        return minV;
    }

    /**
     * @return The bottom-right corner texture X coordinate (0.0 - 1.0).
     */
    public float getMaxU()
    {
        return maxU;
    }

    /**
     * @return The bottom-right corner texture Y coordinate (0.0 - 1.0).
     */
    public float getMaxV()
    {
        return maxV;
    }

    public void setMinU(float minU)
    {
        this.minU = minU;
    }

    public void setMinV(float minV)
    {
        this.minV = minV;
    }

    public void setMaxU(float maxU)
    {
        this.maxU = maxU;
    }

    public void setMaxV(float maxV)
    {
        this.maxV = maxV;
    }

    public static class Builder
    {
        private int textureWidth;
        private int textureHeight;
        private float textureWidthF;
        private float textureHeightF;

        public Builder(int tW, int tH)
        {
            this.textureWidth = tW;
            this.textureHeight = tH;
            this.textureWidthF = (float) this.textureWidth;
            this.textureHeightF = (float) this.textureHeight;

        }

        public TextureRegion build(int u, int v, int w, int h)
        {
            return new TextureRegion(u / textureWidthF, v / textureHeightF, (u + w) / textureWidthF, (v + h) / textureHeightF);
        }
    }
}
