package org.hexworks.cobalt.databinding.value

/**
 * A [WritableValue] is an entity that wraps a value that can be read and set.
 */
interface WritableValue<T> {

    var value: T

}
