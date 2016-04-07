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
package org.libnoctis.layout;


import org.libnoctis.components.NComponent;
import org.libnoctis.components.NContainer;
import org.libnoctis.util.Vector4i;


/**
 * The Noctis Layout
 * <p>
 * The Noctis layout is an objet that can manage the
 * components positions of a container
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @see LayoutProperty
 * @since 1.0.0
 */
public abstract class NLayout
{
	/**
	 * The parent that is using this layout instance
	 */
	@SuppressWarnings("unused")
	private NContainer parent;

	/**
	 * A method executed when the layout is set to a container
	 *
	 * @param parent The container where this layout was added
	 */
	void onSet(NContainer parent)
	{
		this.parent = parent;

		onLayoutSet(parent);
	}

	/**
	 * A method executed when the layout is set to a container
	 *
	 * @param parent The container where this layout was added
	 */
	protected void onLayoutSet(NContainer parent)
	{
	}

	/**
	 * Generate the position and the size of the given elements
	 *
	 * @param components The components to generate the position
	 * @return The position and the size of all the components
	 */
	public abstract Vector4i[] getElementsPosition(NComponent[] components);
}
