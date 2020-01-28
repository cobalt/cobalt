package org.hexworks.cobalt.databinding.api.property

import kotlin.properties.ReadWriteProperty

/**
 * Augments [Property] with the ability to be used as a property delegate.
 */
interface PropertyDelegate<T : Any> : Property<T>, ReadWriteProperty<Any?, T> {
    companion object
}
