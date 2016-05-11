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
package org.libnoctis.layout;

import org.libnoctis.components.NComponent;

/**
 * The Illegal Layout Property Exception
 *
 * <p>
 *     This exception is thrown when a component hasn't
 *     the right illegal layout property type.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class IllegalLayoutPropertyException extends RuntimeException
{
    private static final long serialVersionUID = 6333933366129076054L;

    /**
     * The Illegal Layout Property Exception
     *
     * @param component The component that hasn't the right layout property type
     * @param shouldBe The type of layout property the component should have
     */
    public IllegalLayoutPropertyException(NComponent component, String shouldBe)
    {
        super("A component has as position a " + (component.getProperty() == null ? "null" : component.getProperty().getClass().getSimpleName()) + " but should have a " + shouldBe);
    }
}
