package org.hexworks.cobalt.databinding.api.converter

/**
 * Responsible for converting a value of type [S] to a value of type [T]
 * and vica versa.
 */
interface BiConverter<S, T> {

    /**
     * Converts `source` to an object of type [T].
     */
    fun convertSourceToTarget(source: S): T

    /**
     * Converts `target` to an object of type [S].
     */
    fun convertTargetToSource(target: T): S
}
