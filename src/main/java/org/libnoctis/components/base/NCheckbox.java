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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.libnoctis.components.NComponent;
import org.libnoctis.input.NListener;
import org.libnoctis.input.NoctisEvent;
import org.libnoctis.input.mouse.MousePressedEvent;
import org.libnoctis.ninepatch.LinkedNinePatch;
import org.libnoctis.ninepatch.NoctisNinePatch;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.gl.GlTexture;

/**
 * The NCheckbox
 *
 * <p>
 *     A Noctis Checkbox.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public class NCheckbox extends NComponent
{
    public static final String CHECKBOX_SECTION          = COMPONENTS_SECTION + ".checkbox";
    public static final String CHECKBOX_TEXTURE          = CHECKBOX_SECTION + ".texture";
    public static final String CHECKBOX_TEXTURE_HOVER    = CHECKBOX_TEXTURE + ".hover";
    public static final String CHECKBOX_TEXTURE_DISABLED = CHECKBOX_TEXTURE + ".disabled";
    public static final String CHECKBOX_TEXTURE_CHECK    = CHECKBOX_TEXTURE + ".check";

    /**
     * If the checkbox is checked
     */
    private boolean checked = false;

    /**
     * The checkbox texture
     */
    @NotNull
    private GlTexture texture;

    /**
     * The checkbox texture when the mouse is over
     */
    @Nullable
    private GlTexture hoverTexture;

    /**
     * The checkbox texture when it is disabled
     */
    @Nullable
    private GlTexture disabledTexture;

    /**
     * The check texture
     */
    @NotNull
    private GlTexture checkTexture;

    /**
     * The texture as a nine patch
     */
    @Nullable
    @LinkedNinePatch("texture")
    private NoctisNinePatch texturePatch;

    /**
     * The texture when the mouse is on, as a nine patch.
     */
    @Nullable
    @LinkedNinePatch("hoverTexture")
    private NoctisNinePatch hoverTexturePatch;

    /**
     * The texture when disabled, as a nine patch.
     */
    @Nullable
    @LinkedNinePatch("disabledTexture")
    private NoctisNinePatch disabledTexturePatch;

    /**
     * The check texture, as a nine patch
     */
    @Nullable
    @LinkedNinePatch("checkTexture")
    private NoctisNinePatch checkPatch;

    /**
     * If the checkbox is disabled
     */
    private boolean disabled = false;

    /**
     * The theme property containing the texture path
     */
    @NotNull
    private String texturePath;

    /**
     * The theme property containing the hover texture path
     */
    @NotNull
    private String hoverTexturePath;

    /**
     * The theme property containing the disabled texture path
     */
    @NotNull
    private String disabledTexturePath;

    /**
     * The theme property containing the check texture path
     */
    @NotNull
    private String checkPath;

    /**
     * The Noctis Checkbox
     */
    public NCheckbox()
    {
        this(CHECKBOX_TEXTURE, CHECKBOX_TEXTURE_HOVER, CHECKBOX_TEXTURE_DISABLED, CHECKBOX_TEXTURE_CHECK);
    }

    /**
     * The Noctis Checkbox
     *
     * @param texturePath The theme property containing the texture path
     * @param checkPath The theme property containing the check texture path
     */
    public NCheckbox(@NotNull String texturePath, @NotNull String checkPath)
    {
        this(texturePath, "", "", checkPath);
    }

    /**
     * The Noctis Checkbox
     *
     * @param texturePath The theme property containing the texture path
     * @param hoverTexturePath The theme property containing the hover texture path
     * @param checkPath The theme property containing the check texture path
     */
    public NCheckbox(@NotNull String texturePath, @NotNull String hoverTexturePath, @NotNull String checkPath)
    {
        this(texturePath, hoverTexturePath, "", checkPath);
    }

    /**
     * The Noctis Checkbox
     *
     * @param texturePath The theme property containing the texture path
     * @param hoverTexturePath The theme property containing the hover texture path
     * @param disabledTexturePath The theme property containing the disabled texture path
     * @param checkPath The theme property containing the check texture path
     */
    public NCheckbox(@NotNull String texturePath, @NotNull String hoverTexturePath, @NotNull String disabledTexturePath, @NotNull String checkPath)
    {
        this.texturePath = texturePath;
        this.hoverTexturePath = hoverTexturePath;
        this.disabledTexturePath = disabledTexturePath;
        this.checkPath = checkPath;
    }

    @Override
    protected void init()
    {
        super.init();

        this.registerNinePatch("texturePatch", theme().requireProp(texturePath));
        this.registerNinePatch("hoverTexturePatch", theme().prop(hoverTexturePath));
        this.registerNinePatch("disabledTexturePatch", theme().prop(disabledTexturePath));
        this.registerNinePatch("checkPatch", theme().requireProp(checkPath));

        this.registerListener(new NCheckboxEventListener());
    }

    @Override
    protected void paintComponent(Drawer drawer)
    {
        super.paintComponent(drawer);

        drawer.drawTexture(this.getX(), this.getY(), this.getWidth(), this.getHeight(), disabled ? disabledTexture : (isHovered() && hoverTexture != null ? hoverTexture : texture));

        if (isChecked())
            drawer.drawTexture(this.getX(), this.getY(), this.getWidth(), this.getHeight(), checkTexture);
    }

    private class NCheckboxEventListener implements NListener
    {
        @NoctisEvent
        private void click(MousePressedEvent event)
        {
            if (isHovered())
                setChecked(!checked);
        }
    }

    /**
     * @return If the checkbox is checked
     */
    public boolean isChecked()
    {
        return checked;
    }

    /**
     * Set the checkbox checked or not
     *
     * @param checked If the checkbox is checked or not
     */
    public void setChecked(boolean checked)
    {
        this.checked = checked;

        invalidate();
    }

    /**
     * @return If the component is disabled
     */
    public boolean isDisabled()
    {
        return disabled;
    }

    /**
     * Set the component disabled, or not
     *
     * @param disabled If the component should be disabled
     */
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;

        invalidate();
    }
}
