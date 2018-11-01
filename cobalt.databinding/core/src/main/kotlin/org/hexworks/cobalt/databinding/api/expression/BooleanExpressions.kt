package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue

fun ObservableValue<Boolean>.and(other: ObservableValue<Boolean>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Boolean>.or(other: ObservableValue<Boolean>): Binding<Boolean> {
    TODO()
}

operator fun ObservableValue<Boolean>.not(): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Boolean>.isEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Boolean>.isNotEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Boolean>.asString(): Binding<String> {
    TODO()
}
