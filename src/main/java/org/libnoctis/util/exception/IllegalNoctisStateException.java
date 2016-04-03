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
package org.libnoctis.util.exception;

public class IllegalNoctisStateException extends NoctisException
{
	private static final long serialVersionUID = -1111479383577938294L;

	public IllegalNoctisStateException()
	{
		super();
	}

	public IllegalNoctisStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalNoctisStateException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IllegalNoctisStateException(String message)
	{
		super(message);
	}

	public IllegalNoctisStateException(Throwable cause)
	{
		super(cause);
	}
}
