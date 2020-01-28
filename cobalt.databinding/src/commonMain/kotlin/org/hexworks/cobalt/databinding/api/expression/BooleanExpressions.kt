package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Boolean>.and(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue && otherValue }
}

fun ObservableValue<Boolean>.and(other: Boolean): Binding<Boolean> {
    return ComputedBinding(this) { it && other }
}

fun ObservableValue<Boolean>.or(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue || otherValue }
}

fun ObservableValue<Boolean>.or(other: Boolean): Binding<Boolean> {
    return ComputedBinding(this) { it || other }
}

operator fun ObservableValue<Boolean>.not(): Binding<Boolean> {
    return ComputedBinding(this) { it.not() }
}

fun ObservableValue<Boolean>.isEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

fun ObservableValue<Boolean>.isEqualTo(other: Boolean): Binding<Boolean> {
    return ComputedBinding(this) { it == other }
}

fun ObservableValue<Boolean>.isNotEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue }
}

fun ObservableValue<Boolean>.isNotEqualTo(other: Boolean): Binding<Boolean> {
    return ComputedBinding(this) { it != other }
}

fun ObservableValue<Boolean>.asString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
