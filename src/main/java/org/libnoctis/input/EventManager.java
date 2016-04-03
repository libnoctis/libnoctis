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
package org.libnoctis.input;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * The Event Manager
 * <p>
 * The Event Manager can manage, check, and launch some events of classes.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class EventManager
{
	/**
	 * The list of the listeners
	 */
	private ArrayList<Object> listeners = new ArrayList<Object>();

	/**
	 * Dispatch an event to all the listeners
	 *
	 * @param event The instance of the event to call
	 */
	public void callEvent(Event event)
	{
		try
		{
			for (Object listener : listeners)
			{
				launchEvent(listener, event);
			}
		}
		catch (Throwable e)
		{}
	}

	/**
	 * Register an event listener
	 *
	 * @param listener The listener containing the events
	 */
	public void registerListener(Object listener)
	{
		listeners.add(listener);
	}

	/**
	 * Launch an event
	 *
	 * @param listener The object that holds event method.
	 * @param event The class (ex:
	 *        {@link org.libnoctis.input.mouse.MouseMoveEvent} of the
	 *        event
	 * @see NoctisEvent
	 */
	private void launchEvent(Object listener, Event event) throws Throwable
	{
		Method[] methods = listener.getClass().getDeclaredMethods();
		for (Method method : methods)
		{
			if (method.getParameterTypes().length != 1)
				return;

			Annotation[] annotations = method.getDeclaredAnnotations();
			for (Annotation annotation : annotations)
				if (annotation.annotationType().equals(NoctisEvent.class) && (method.getParameterTypes()[0].equals(event.getClass()) || (event.shouldPassForSuperclassEvent() && method.getParameterTypes()[0].isInstance(event))))
				{
					method.setAccessible(true);

					try
					{
						method.invoke(listener, event);
					}
					catch (IllegalAccessException ignored)
					{
						// Can't happen
					}
					catch (InvocationTargetException e)
					{
						throw e.getTargetException();
					}
				}
		}
	}
}
