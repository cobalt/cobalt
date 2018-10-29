package org.hexworks.cobalt.datatypes.value

/**
 * A [WritableValue] is an entity that wraps a value that can be read and set.
 */
interface WritableValue<T> {

    var value: T

}
