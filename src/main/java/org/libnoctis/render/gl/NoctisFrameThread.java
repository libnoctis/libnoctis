/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Libnoctis is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.render.gl;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.libnoctis.components.NFrame;
import org.lwjgl.opengl.Display;


import static org.lwjgl.opengl.GL11.*;


/**
 * A frame render Thread.
 * 
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisFrameThread extends Thread
{
    /**
     * {@code true} is this Thread should keep running.
     */
    private boolean isRunning;

    /**
     * Ordered collection of tasks that should be executed is this Thread.
     */
    private CopyOnWriteArrayList<Runnable> runnables = new CopyOnWriteArrayList<Runnable>();

    /**
     * The frame rendered by this Thread.
     */
    private NFrame frame;

    /**
     * The Noctis Frame Thread
     *
     * @param frame The frame of this thread
     */
    public NoctisFrameThread(NFrame frame)
    {
        isRunning = true;
        this.frame = frame;
    }

    @Override
    public void run()
    {
        callRunnables();

        while (isRunning)
        {
            // Temporary, needed because a bug will throw else.
            callRunnables();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            if (Display.wasResized())
            {
                frame.resize();
            }

            if (Display.isCloseRequested())
            {
                frame.requestClose();
            }

            // - Listen to user input
            frame.input();

            // - Render pass
            {
                frame.getDrawer().preRender();

                // Render frame and its children
                frame.render();

                frame.getDrawer().postRender();
            }

            // Swap buffers
            Display.update();

            // Sync FPS as needed by the frame object
            Display.sync(frame.getFPS());
        }
    }

    /**
     * Schedules the given task to be executed before next render pass.
     * 
     * @param runnable The task to be executed in this Thread.
     */
    public void runLater(Runnable runnable)
    {
        synchronized (runnables)
        {
            runnables.add(runnable);
        }
    }

    /**
     * Call the runnables (of the runnables list)
     */
    private void callRunnables()
    {
        synchronized (runnables)
        {
            for (Iterator<Runnable> iterator = runnables.iterator(); iterator.hasNext();)
            {
                Runnable runnable = iterator.next();
                runnable.run();

                runnables.remove(runnable);
            }

            if (runnables.size() > 0)
                callRunnables();
        }
    }

    /**
     * Set the thread running or not
     *
     * @param b If it should be running or not
     */
    public void setRunning(boolean b)
    {
        isRunning = b;
    }
}
