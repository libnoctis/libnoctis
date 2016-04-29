package org.libnoctis.ninepatch;

import org.libnoctis.render.NTexture;
import org.libnoctis.util.Vector2i;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class NoctisNinePatch
{
    private BufferedImage        image;
    private NoctisNinePatchChunk chunk;

    public NoctisNinePatch(BufferedImage image, NoctisNinePatchChunk chunk)
    {
        this.image = image;
        this.chunk = chunk;
    }

    public NTexture generateFor(int width, int height)
    {
        return generateFor(new Vector2i(width, height));
    }

    public NTexture generateFor(Vector2i dimensions)
    {
        BufferedImage generated = CompatibleImageMaker.translucent(dimensions);
        Graphics2D g = (Graphics2D) generated.getGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        if (this.chunk.getPatches().isEmpty())
            return new NTexture(this.image);

        DrawingData data = computePatches(dimensions);

        int x = 0;
        int y = 0;

        int fixedIndex = 0;
        int horizontalIndex = 0;
        int verticalIndex = 0;
        int patchIndex = 0;

        boolean hStretch;
        boolean vStretch;

        float vWeightSum = 1.0f;
        float vRemainder = data.getVerticalRemainder();

        vStretch = this.getChunk().isVerticalStartsWithPatch();
        while (y < dimensions.getY() - 1)
        {
            hStretch = this.getChunk().isHorizontalStartsWithPatch();

            int height = 0;
            float vExtra = 0.0f;

            float hWeightSum = 1.0f;
            float hRemainder = data.getHorizontalRemainder();

            while (x < dimensions.getX() - 1)
            {
                Rectangle r;

                if (!vStretch)
                {
                    if (hStretch)
                    {
                        r = this.getChunk().getHorizontalPatches().get(horizontalIndex++);

                        float extra = r.width / data.getHorizontalPatchesSum();
                        int width = (int) (extra * hRemainder / hWeightSum);

                        hWeightSum -= extra;
                        hRemainder -= width;

                        g.drawImage(image, x, y, x + width, y + r.height, r.x, r.y, r.x + r.width, r.y + r.height, null);

                        x += width;
                    }
                    else
                    {
                        r = this.getChunk().getFixed().get(fixedIndex++);

                        g.drawImage(image, x, y, x + r.width, y + r.height, r.x, r.y, r.x + r.width, r.y + r.height, null);

                        x += r.width;
                    }

                    height = r.height;
                }
                else
                {
                    if (hStretch)
                    {
                        r = this.getChunk().getPatches().get(patchIndex++);
                        vExtra = r.height / data.getVerticalPatchesSum();
                        height = (int) (vExtra * vRemainder / vWeightSum);

                        float extra = r.width / data.getHorizontalPatchesSum();
                        int width = (int) (extra * hRemainder / hWeightSum);

                        hWeightSum -= extra;
                        hRemainder -= width;

                        g.drawImage(image, x, y, x + width, y + height, r.x, r.y, r.x + r.width, r.y + r.height, null);

                        x += width;
                    }
                    else
                    {
                        r = this.getChunk().getVerticalPatches().get(verticalIndex++);
                        vExtra = r.height / data.getVerticalPatchesSum();
                        height = (int) (vExtra * vRemainder / vWeightSum);

                        g.drawImage(image, x, y, x + r.width, y + height, r.x, r.y, r.x + r.width, r.y + r.height, null);

                        x += r.width;
                    }

                }

                hStretch = !hStretch;
            }

            x = 0;
            y += height;

            if (vStretch)
            {
                vWeightSum -= vExtra;
                vRemainder -= height;
            }

            vStretch = !vStretch;
        }

        g.dispose();

        return new NTexture(generated);
    }

    private DrawingData computePatches(Vector2i dimensions)
    {
        DrawingData data = new DrawingData();

        boolean measuredWidth = false;
        boolean endRow = true;

        int remainderHorizontal = 0;
        int remainderVertical = 0;

        if (this.getChunk().getFixed().size() > 0)
        {
            int start = this.getChunk().getFixed().get(0).y;

            for (Rectangle rect : this.getChunk().getFixed())
            {
                if (rect.y > start)
                    endRow = measuredWidth = true;

                if (!measuredWidth)
                    remainderHorizontal += rect.width;

                if (endRow)
                {
                    remainderVertical += rect.height;
                    endRow = false;
                    start = rect.y;
                }
            }
        }

        data.setHorizontalPatchesSum(dimensions.getX() - remainderHorizontal);
        data.setVerticalRemainder(dimensions.getY() - remainderVertical);

        data.setHorizontalPatchesSum(0);

        int start = -1;

        for (Rectangle rect : this.getChunk().getHorizontalPatches().isEmpty() ? this.getChunk().getPatches() : this.getChunk().getHorizontalPatches())
            if (rect.x > start)
            {
                data.setHorizontalPatchesSum(data.getHorizontalPatchesSum() + rect.width);
                start = rect.x;
            }

        data.setVerticalPatchesSum(0);

        start = -1;

        for (Rectangle rect : this.getChunk().getVerticalPatches().isEmpty() ? this.getChunk().getPatches() : this.getChunk().getVerticalPatches())
        {
            if (rect.y > start)
            {
                data.setVerticalPatchesSum(data.getVerticalPatchesSum() + rect.height);
                start = rect.y;
            }
        }

        return data;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    public NoctisNinePatchChunk getChunk()
    {
        return chunk;
    }

    public void setChunk(NoctisNinePatchChunk chunk)
    {
        this.chunk = chunk;
    }
}
