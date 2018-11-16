package org.hexworks.cobalt.databinding.api.converter

/**
 * SAM for converting a value of type [S] to a value of type [T]
 */
interface Converter<in S, out T> {

    /**
     * Converts `source` to an object of type [T].
     */
    fun convert(source: S): T
}
