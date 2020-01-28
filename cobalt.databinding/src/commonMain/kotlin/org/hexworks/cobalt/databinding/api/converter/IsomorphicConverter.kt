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

    fun reverseConverter(): Converter<T, S> = object : Converter<T, S> {

        override fun convert(source: T): S {
            return convertBack(source)
        }
    }

    companion object
}
