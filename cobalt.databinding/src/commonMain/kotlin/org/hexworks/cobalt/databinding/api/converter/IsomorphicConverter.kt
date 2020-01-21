package org.hexworks.cobalt.databinding.api.converter

/**
 * Responsible for converting a value of type [S] to a value of type [T]
 * and vice versa.
 */
interface IsomorphicConverter<S, T> : Converter<S, T> {

    /**
     * Converts `target` to an object of type [S].
     */
    fun convertBack(target: T): S
}
