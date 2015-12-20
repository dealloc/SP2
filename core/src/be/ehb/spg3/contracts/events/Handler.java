package be.ehb.spg3.contracts.events;

import java.lang.annotation.*;

// Created by Wannes Gennar. All rights reserved

/**
 * Marks a method as an event handler.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Handler
{
}
