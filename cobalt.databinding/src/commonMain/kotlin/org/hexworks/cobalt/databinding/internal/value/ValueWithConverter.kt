package org.hexworks.cobalt.databinding.internal.value

import org.hexworks.cobalt.databinding.api.converter.Converter
import org.hexworks.cobalt.databinding.api.value.ObservableValue

data class ValueWithConverter<S : Any, T : Any>(
        val value: ObservableValue<S>,
        val converter: Converter<S, T>)