package org.libnoctis.test;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.lwjgl.LWJGLUtil;


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
 * @author Modified by Wytrem
 */
public class LWJGLSetup
{
    private static boolean loaded;

    /**
     * Load LWJGL in given folder
     */
    public static void load(File folder) throws IOException
    {
        if (!loaded)
        {
            if (!folder.exists())
            {
                folder.mkdirs();
            }

            if (folder.isDirectory())
            {
                File nativesFolder = new File(folder, "natives");

                if (!nativesFolder.exists())
                {
                    nativesFolder.mkdirs();
                }

                int os = LWJGLUtil.getPlatform();
                if (os == LWJGLUtil.PLATFORM_WINDOWS)
                {
                    if (!new File(nativesFolder.getPath() + "/jinput-dx8_64.dll").exists())
                    {
                        extractFromClasspath("jinput-dx8_64.dll", nativesFolder);
                        extractFromClasspath("jinput-dx8.dll", nativesFolder);
                        extractFromClasspath("jinput-raw_64.dll", nativesFolder);
                        extractFromClasspath("jinput-raw.dll", nativesFolder);
                        extractFromClasspath("lwjgl.dll", nativesFolder);
                        extractFromClasspath("lwjgl64.dll", nativesFolder);
                        extractFromClasspath("OpenAL32.dll", nativesFolder);
                        extractFromClasspath("OpenAL64.dll", nativesFolder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }
                }
                else if (os == LWJGLUtil.PLATFORM_LINUX)
                {
                    if (!new File(nativesFolder.getPath() + "/liblwjgl.so").exists())
                    {
                        extractFromClasspath("liblwjgl.so", nativesFolder);
                        extractFromClasspath("liblwjgl64.so", nativesFolder);
                        extractFromClasspath("libopenal.so", nativesFolder);
                        extractFromClasspath("libopenal64.so", nativesFolder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }

                }
                else if (os == LWJGLUtil.PLATFORM_MACOSX)
                {
                    if (!new File(nativesFolder.getPath() + "/openal.dylib").exists())
                    {
                        extractFromClasspath("liblwjgl.jnilib", nativesFolder);
                        extractFromClasspath("liblwjgl-osx.jnilib", nativesFolder);
                        extractFromClasspath("openal.dylib", nativesFolder);
                    }
                    else
                    {
                        System.out.println("Natives already exist.");
                    }
                }
                else
                {
                    System.err.println("Operating System couldn't be iditified");
                }
                System.setProperty("net.java.games.input.librarypath", nativesFolder.getAbsolutePath());
                System.setProperty("org.lwjgl.librarypath", nativesFolder.getAbsolutePath());
            }
            loaded = true;
        }
    }

    /**
     * Extract given file from classpath into given folder
     */
    private static void extractFromClasspath(String fileName, File folder) throws IOException
    {
        FileOutputStream out = new FileOutputStream(new File(folder, fileName));
        IOUtils.copy(LWJGLSetup.class.getResourceAsStream("/" + fileName), out);
        out.flush();
        out.close();
    }
}