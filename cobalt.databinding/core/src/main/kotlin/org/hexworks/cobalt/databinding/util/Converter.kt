package org.hexworks.cobalt.databinding.util

interface Converter<S, T> {

    fun convertSourceToTarget(source: S): T

    fun convertTargetToSource(target: T): S
}
