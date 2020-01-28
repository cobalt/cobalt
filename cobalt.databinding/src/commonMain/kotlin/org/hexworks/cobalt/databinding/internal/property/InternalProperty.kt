package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.logging.api.Logger

interface InternalProperty<T : Any> : Property<T> {

    val logger: Logger
    val id: Identifier
    val propertyScope: PropertyScope

    /**
     * Tries to update the [value] of this [InternalProperty] to [newValue] using [event]
     * to check for circular dependencies. This function is only for internal usage.
     * @return `true` if change happened, `false` if not
     */
    fun updateWithEvent(
            oldValue: T,
            newValue: T,
            event: ObservableValueChanged<Any>): Boolean
}