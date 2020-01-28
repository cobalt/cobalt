package org.hexworks.cobalt.databinding.internal.value

import org.hexworks.cobalt.core.internal.toAtom
import org.hexworks.cobalt.core.platform.factory.IdentifierFactory
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo

/**
 * A [CompositeObservableValue] combines the given values into a single
 * value. Whenever the underlying value changes, they will be converted and
 * [value] will be updated to reflect the new values.
 */
class CompositeObservableValue<T : Any>(
        initialValue: T,
        sources: Iterable<ValueWithConverter<Any, T>>
) : ObservableValue<T> {

    private val id = IdentifierFactory.randomIdentifier()
    private val propertyScope = PropertyScope(id)
    private val atom = initialValue.toAtom()

    override val value: T
        get() = atom.get()

    init {
        sources.forEach { (value, converter) ->
            value.onChange { event: ObservableValueChanged<Any> ->
                val (_, newValue) = event
                atom.transform { oldValue ->
                    val convertedValue = converter.convert(newValue)
                    Cobalt.eventbus.publish(
                            event = ObservableValueChanged(
                                    oldValue = oldValue,
                                    newValue = newValue,
                                    observableValue = this,
                                    emitter = this,
                                    trace = listOf(event) + event.trace),
                            eventScope = propertyScope)
                    convertedValue
                }
            }
        }
    }

    override fun onChange(fn: (ObservableValueChanged<T>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribeTo<ObservableValueChanged<T>>(propertyScope) {
            fn(it)
        }
    }

}