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
package org.libnoctis.theme;

/**
 * The Theme Required Exception
 *
 * <p>
 *     An exception thrown when a component or something, can't
 *     find a needed object in the current theme.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public class ThemeRequiredException extends RuntimeException
{
    private static final long serialVersionUID = -6354309819719404838L;

    /**
     * The ThemeRequiredException
     *
     * @param message The exception message
     */
    public ThemeRequiredException(String message)
    {
        super(message);
    }

    /**
     * The ThemeRequiredException
     *
     * @param message The exception message
     * @param cause The exception cause
     */
    public ThemeRequiredException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
