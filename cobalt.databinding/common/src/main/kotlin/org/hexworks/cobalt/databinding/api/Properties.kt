package org.hexworks.cobalt.databinding.api

import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty

object Properties {

    fun <T : Any> propertyFrom(obj: T): Property<T> = DefaultProperty(obj)
}

fun <T : Any> createPropertyFrom(obj: T): Property<T> = Properties.propertyFrom(obj)
