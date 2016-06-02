package org.libnoctis.theme;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Theme Require Property Annotation
 *
 * <p>
 *     This annotation marks a field as automatically filled
 *     from a theme property (the property name is given as value).
 *
 *     Also if the property can't be founded, a ThemeRequiredException
 *     (Runtime) will be thrown.
 *
 *     (Works only in the classes extending NComponent)
 *
 *     Also you can use it on a
 *     {@link org.libnoctis.ninepatch.LinkedNinePatch},
 *     and it will do the same as
 *     {@link org.libnoctis.components.NComponent#registerNinePatch(String, String)}
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ThemeRequireProperty
{
    /**
     * The property to get, from the current theme
     *
     * @return The read property
     */
    String value();
}
