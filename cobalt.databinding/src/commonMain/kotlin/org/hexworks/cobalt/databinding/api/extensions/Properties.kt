@file:JvmName("Properties")

package org.hexworks.cobalt.databinding.api.extensions

import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.jvm.JvmName

/**
 * Creates a new [Property] from the given object [obj].
 */
fun <T : Any> createPropertyFrom(
        obj: T,
        validator: (T) -> Boolean = { true }): Property<T> =
        DefaultProperty(
                initialValue = obj,
                validator = validator)

/**
 * Creates a new [Property] from the given object of type [T].
 */
fun <T : Any> T.toProperty(
        validator: (T) -> Boolean = { true }): Property<T> =
        createPropertyFrom(
                obj = this,
                validator = validator)

