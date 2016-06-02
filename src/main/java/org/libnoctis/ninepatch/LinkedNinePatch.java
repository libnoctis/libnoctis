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
package org.libnoctis.ninepatch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Linked Nine Patch Annotation
 *
 * <p>
 *     This annotation can be added on a NComponent field
 *     (should be a NoctisNinePatch) to mark it internally managed.
 *     It will be linked to a GlTexture with the same given name.
 *     (Give the name of the field of the GLTexture).
 *
 *     To register it then, use the
 *     {@link org.libnoctis.components.NComponent#registerNinePatch}
 *     method.
 *
 *     Also it will be automatically updated when the component change
 *     it size.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LinkedNinePatch
{
    /**
     * This should be the name of the GlTexture field linked to this NinePatch
     * where it will be generated.
     *
     * @return The name of the GlTexture field linked to this patch.
     */
    String value();
}
