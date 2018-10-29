package org.hexworks.cobalt.databinding.api.util

interface Converter<S, T> {

    fun convertSourceToTarget(source: S): T

    fun convertTargetToSource(target: T): S
}
