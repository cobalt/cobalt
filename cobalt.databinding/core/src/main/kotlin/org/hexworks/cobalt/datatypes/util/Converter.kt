package org.hexworks.cobalt.datatypes.util

interface Converter<S, T> {

    fun convertSourceToTarget(source: S): T

    fun convertTargetToSource(target: T): S
}
