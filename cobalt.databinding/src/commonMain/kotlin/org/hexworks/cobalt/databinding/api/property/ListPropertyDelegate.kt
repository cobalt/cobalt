package org.hexworks.cobalt.databinding.api.property

import org.hexworks.cobalt.databinding.api.collections.ListProperty
import kotlin.properties.ReadOnlyProperty

/**
 * Augments [ListProperty] with the ability to be used as a property delegate.
 */
interface ListPropertyDelegate<T : Any> : ListProperty<T>, ReadOnlyProperty<Any?, ListProperty<T>> {

    companion object
}
