package org.hexworks.cobalt.databinding.api.extensions

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.BiConverter
import org.hexworks.cobalt.databinding.api.converter.Converter
import org.hexworks.cobalt.databinding.api.property.Property

/**
 * @see [Property.bindBidirectional].
 */
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

/**
 * @see [Property.bind]
 */
inline fun <S : Any, T : Any> Property<S>.bind(
        other: Property<T>,
        crossinline converter: (T) -> S) {
    this.bind(other, object : Converter<T, S> {
        override fun convert(source: T): S {
            return converter(source)
        }
    })
}
