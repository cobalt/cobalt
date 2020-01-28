package org.hexworks.cobalt.databinding.api.value

/**
 * Represents a value which can be read.
 */
interface Value<out T: Any> {

    val value: T

    companion object
}
