package org.hexworks.cobalt.databinding.internal.collections

import org.hexworks.cobalt.databinding.api.collections.ListProperty
import org.hexworks.cobalt.databinding.api.property.ListPropertyDelegate
import kotlin.reflect.KProperty

class DefaultListPropertyDelegate<T : Any>(private val property: ListProperty<T>)
    : ListPropertyDelegate<T>, ListProperty<T> by property {

    override fun getValue(thisRef: Any?, property: KProperty<*>): ListProperty<T> {
        return this.property
    }

}
