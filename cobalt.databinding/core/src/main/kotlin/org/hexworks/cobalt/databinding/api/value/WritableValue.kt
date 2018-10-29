package org.hexworks.cobalt.databinding.api.value

/**
 * A [WritableValue] is an entity that wraps a value that can be read and set.
 */
interface WritableValue<T : Any> {

    var value: T

}
