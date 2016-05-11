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
package org.libnoctis.components.base;

import java.awt.Rectangle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.keyboard.Key;
import org.libnoctis.input.keyboard.KeyPressedEvent;
import org.libnoctis.input.keyboard.KeyReleasedEvent;
import org.libnoctis.input.mouse.MouseClickedEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;
import org.libnoctis.util.NoctisNinePatch;
import org.libnoctis.util.NoctisNinePatchCache;
import org.libnoctis.util.Vector2i;

/**
 * The Noctis Text Field
 *
 * <p>
 *     A text field. Type type type.
 *     Type.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NTextField extends NComponent
{
    public static final String TEXTFIELD_SECTION = COMPONENTS_SECTION + ".textfield";
    public static final String TEXTFIELD_TEXTURE = TEXTFIELD_SECTION + ".texture";
    public static final String TEXTFIELD_TEXTURE_HOVER = TEXTFIELD_SECTION + ".texture.hover";

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

    /**
     * The background nine patch, null if #background is not
     */
    @Nullable
    private NoctisNinePatch backgroundPatch;

    /**
     * The background when focused, as a nine patch.
     */
    @Nullable
    private NoctisNinePatch focusBackgroundPatch;

    /**
     * The background nine patch, null if #backgroundPatch is not
     */
    @NotNull
    private GlTexture background;

    /**
     * The background when focused
     */
    @Nullable
    private GlTexture focusBackground;

    /**
     * The coords of the field where is the text
     */
    private Vector2i textPadding;

    /**
     * Focus sur le text field (en cours d'ecriture)
     */
    private boolean focus = false;

    @Override
    protected void init()
    {
        super.init();

        String background = theme().requireProp(TEXTFIELD_TEXTURE);
        String backgroundFocused = theme().prop(TEXTFIELD_TEXTURE_HOVER);

        if (background.endsWith(".9.png"))
            this.backgroundPatch = NoctisNinePatchCache.fromPath(theme(), background);
        else
            this.background = theme().requireTexture(background);

        if (backgroundFocused != null)
            if (backgroundFocused.endsWith(".9.png"))
                this.focusBackgroundPatch = NoctisNinePatchCache.fromPath(theme(), backgroundFocused);
            else
                this.focusBackground = theme().requireTexture(backgroundFocused);

        int xPadding = Integer.parseInt(theme().requireProp("component.textfield.textpadding.x"));
        int yPadding = Integer.parseInt(theme().requireProp("component.textfield.textpadding.y"));

        this.textPadding = new Vector2i(xPadding, yPadding);

        this.registerListener(new NTextFieldListener());

        updateNinePatches();
    }

    public class NTextFieldListener implements NListener
    {
        @NoctisEvent
        private void keyPress(KeyPressedEvent event)
        {
            if (!focus)
                return;

            System.out.println("Penis !");
            if (event.getKey().isCharacter())
            {
                setText(getText().substring(0, cursorPos) + ((currentShift != null || capsLock) && event.getKey().hasUpperCharacter() ? event.getKey().getUpperCharacter() : event.getKey().getCharacter()) + getText().substring(cursorPos, getText().length()));
                cursorPos++;
            }
            else if (event.getKey() == Key.KEY_RIGHT)
                setCursorPos(getCursorPos() + 1);
            else if (event.getKey() == Key.KEY_LEFT && cursorPos != 0)
                setCursorPos(getCursorPos() - 1);
            else if (event.getKey() == Key.KEY_BACK && cursorPos != 0)
            {
                setText(getText().substring(0, cursorPos - 1) + getText().substring(cursorPos, getText().length()));
                cursorPos++;
            }
            else if (event.getKey() == Key.KEY_LSHIFT && event.getKey() == Key.KEY_RSHIFT)
                currentShift = event.getKey();
            else if (event.getKey() == Key.KEY_CAPITAL)
                capsLock = !capsLock;
        }

        @NoctisEvent
        private void mousePress(MousePressedEvent event)
        {
            Rectangle rectangle = new Rectangle(getX() + textPadding.getX(), getY() + textPadding.getY(), getWidth() - textPadding.getX(), getHeight() - textPadding.getY());
            focus = rectangle.contains(event.getPos().getX(), event.getPos().getY());
        }

        @NoctisEvent
        private void keyRelease(KeyReleasedEvent event)
        {
            if (event.getKey() == currentShift)
                currentShift = null;
        }
    }

    private void updateNinePatches()
    {
        if (backgroundPatch == null)
        {
            return;
        }

        Vector2i dimensions = new Vector2i(this.getWidth(), this.getHeight());

        background = backgroundPatch.generateFor(dimensions);
        focusBackground = focusBackgroundPatch.generateFor(dimensions);
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        // Drawing background (or focused background)
        GlTexture texture = focus && focusBackground != null ? focusBackground : background;

        drawer.drawTexture(getX(), getY(), this.getWidth(), this.getHeight(), texture);

        // Drawing the text
        drawer.drawString(this.getText() + /* The caret */ (focus ? "_" : ""), getX() + this.textPadding.getX(), getY() + this.textPadding.getY());
    }

    @Override
    public void setWidth(int width)
    {
        super.setWidth(width);

        updateNinePatches();
    }

    @Override
    public void setHeight(int height)
    {
        super.setHeight(height);

        updateNinePatches();
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

    /**
     * @return The background texture, as a nine patch. Can be null if it
     *          is not a nine patch (if it is, use #getBackground)
     */
    @Nullable
    public NoctisNinePatch getBackgroundPatch()
    {
        return backgroundPatch;
    }

    /**
     * @return The background texture, can be null if it is a nine patch
     *          (if it is, use #getBackgroundPatch)
     */
    @Nullable
    public GlTexture getBackground()
    {
        return background;
    }

    /**
     * @return The background texture when focused, as a nine patch. Can be null
     * if it is not a nine patch (if it is, use #getFocusBackground) or if the theme
     * hasn't
     */
    @Nullable
    public NoctisNinePatch getFocusBackgroundPatch()
    {
        return focusBackgroundPatch;
    }

    /**
     * @return The background texture, can be null if it is not a nine patch
     *          (if it is, use #getFocusBackgroundPatch)
     */
    @Nullable
    public GlTexture getFocusBackground()
    {
        return focusBackground;
    }

    /**
     * @return If the text field is focused (the user is typing in)
     */
    public boolean isFocus()
    {
        return focus;
    }

    /**
     * @return The coords of the field where the text should be
     */
    public Vector2i getTextPadding()
    {
        return textPadding;
    }

    /**
     * @return If the user is typing in the text field
     */
    public boolean isFocused()
    {
        return focus;
    }
}
