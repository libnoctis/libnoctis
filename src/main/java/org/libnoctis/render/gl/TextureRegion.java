/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.render.gl;

/**
 * The Texture Region
 *
 * <p>
 *     The texture region is a part of a texture.
 * </p>
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
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
