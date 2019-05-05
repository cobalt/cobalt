package org.hexworks.cobalt.databinding.api.value

/**
 * A [WritableValue] wraps a value which can be read and written.
 */
interface WritableValue<T : Any> : Value<T> {

    override var value: T

}
