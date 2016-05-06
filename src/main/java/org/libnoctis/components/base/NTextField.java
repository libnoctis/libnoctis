/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 * This file is part of Libnoctis.
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.components.base;


import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.keyboard.Key;
import org.libnoctis.input.keyboard.KeyPressedEvent;
import org.libnoctis.input.keyboard.KeyReleasedEvent;
import org.libnoctis.render.Drawer;


/**
 * The Noctis Text Field
 * <p>
 * A text field. Type type type.
 * Type.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NTextField extends NComponent implements NListener
{
    /**
     * The current text field text
     */
    private String text = "";

    /**
     * The cursor position
     */
    private int cursorPos = 0;

    /**
     * The current pressed shift key
     */
    private Key currentShift = null;

    /**
     * If caps lock is enabled
     */
    private boolean capsLock = false;

    @NoctisEvent
    private void onKeyPressed(KeyPressedEvent event)
    {
        if (event.getKey().isCharacter())
            setText(getText().substring(0, cursorPos) + ((currentShift != null || capsLock) && event.getKey().hasUpperCharacter() ? event.getKey().getUpperCharacter() : event.getKey().getCharacter()) + getText().substring(cursorPos, getText().length()));
        else if (event.getKey() == Key.KEY_RIGHT)
            setCursorPos(getCursorPos() + 1);
        else if (event.getKey() == Key.KEY_LEFT && cursorPos != 0)
            setCursorPos(getCursorPos() - 1);
        else if (event.getKey() == Key.KEY_BACK && cursorPos != 0)
            setText(getText().substring(0, cursorPos - 1) + getText().substring(cursorPos, getText().length()));
        else if (event.getKey() == Key.KEY_LSHIFT && event.getKey() == Key.KEY_RSHIFT)
            currentShift = event.getKey();
        else if (event.getKey() == Key.KEY_CAPITAL)
            capsLock = !capsLock;
    }

    @NoctisEvent
    private void onKeyReleased(KeyReleasedEvent event)
    {
        if (event.getKey() == currentShift)
            currentShift = null;
    }

    @Override
    protected void onComponentAdded(NContainer parent)
    {
        super.onComponentAdded(parent);

        this.registerListener(this);
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

    }

    /**
     * @return The current text field text
     */
    public String getText()
    {
        return text;
    }

    /**
     * Set the text field current text
     *
     * @param text The new field text
     */
    public void setText(String text)
    {
        this.text = text;
        repaint();
    }

    /**
     * @return The current cursor position
     */
    public int getCursorPos()
    {
        return cursorPos;
    }

    /**
     * Set the cursor position
     *
     * @param cursorPos The new cursor position
     */
    public void setCursorPos(int cursorPos)
    {
        this.cursorPos = cursorPos;
        repaint();
    }
}
