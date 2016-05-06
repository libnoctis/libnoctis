package org.libnoctis.test;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;


/**
 * The MIT License (MIT)
 * Copyright (c) 2014
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ourcraft team - https://github.com/OurCraft/OurCraft/
 */
public final class IOUtils
{
    public static OutputStream copy(InputStream is, OutputStream os) throws IOException
    {
        int i = 0;
        byte[] buffer = new byte[65565];
        while ((i = is.read(buffer, 0, buffer.length)) != -1)
        {
            os.write(buffer, 0, i);
        }
        return os;
    }

    public static OutputStream copy(InputStream in, String output) throws FileNotFoundException, IOException
    {
        return copy(in, new BufferedOutputStream(new FileOutputStream(output)));
    }

    public static String readString(InputStream in, String charset) throws UnsupportedEncodingException, IOException
    {
        return new String(read(in), charset);
    }

    public static byte[] read(InputStream in) throws IOException
    {
        byte[] buffer = new byte[65565];
        ByteArrayOutputStream ous = new ByteArrayOutputStream(buffer.length);
        int i = 0;
        while ((i = in.read(buffer, 0, buffer.length)) != -1)
        {
            ous.write(buffer, 0, i);
        }
        ous.close();
        return ous.toByteArray();
    }

    public static void deleteFolderContents(File folder)
    {
        File[] files = folder.listFiles();
        if (files != null)
            for (File f : files)
            {
                if (f.isDirectory())
                {
                    deleteFolderContents(f);
                    f.delete();
                }
                else
                    f.delete();
            }
    }

    public static FloatBuffer createFloatBuffer(int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size)
    {
        return BufferUtils.createIntBuffer(size);
    }

    public static ByteBuffer createByteBuffer(int size)
    {
        return BufferUtils.createByteBuffer(size);
    }

    public static IntBuffer createFlippedIntBuffer(int... indices)
    {
        IntBuffer buffer = createIntBuffer(indices.length);
        for (int i = 0; i < indices.length; i++)
            buffer.put(indices[i]);
        buffer.flip();
        return buffer;
    }

    public static ByteBuffer createFlippedByteBuffer(byte... bufferData)
    {
        ByteBuffer buf = createByteBuffer(bufferData.length);
        for (int i = 0; i < bufferData.length; i++)
            buf.put(bufferData[i]);
        buf.flip();
        return buf;
    }

    public static FloatBuffer createFlippedFloatBuffer(float... data)
    {
        FloatBuffer buf = createFloatBuffer(data.length);
        for (int i = 0; i < data.length; i++)
            buf.put(data[i]);
        buf.flip();
        return buf;
    }
}
