package org.hexworks.cobalt.databinding.api.extensions

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.converter.BiConverter

inline fun <S : Any, T : Any> Property<S>.bindBidirectional(
        other: Property<T>,
        crossinline sourceConverter: (S) -> T,
        crossinline targetConverter: (T) -> S): Binding<S> {
    return this.bindBidirectional(other, object : BiConverter<S, T> {
        override fun convertSourceToTarget(source: S): T {
            return sourceConverter(source)
        }

        override fun convertTargetToSource(target: T): S {
            return targetConverter(target)
        }
    })
}
