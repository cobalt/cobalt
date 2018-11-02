package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBiBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedSingleBinding

fun ObservableValue<Boolean>.and(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue && otherValue }
}

fun ObservableValue<Boolean>.and(other: Boolean): Binding<Boolean> {
    return ComputedSingleBinding(this) { it && other }
}

fun ObservableValue<Boolean>.or(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue || otherValue }
}

fun ObservableValue<Boolean>.or(other: Boolean): Binding<Boolean> {
    return ComputedSingleBinding(this) { it || other }
}

operator fun ObservableValue<Boolean>.not(): Binding<Boolean> {
    return ComputedSingleBinding(this) { it.not() }
}

fun ObservableValue<Boolean>.isEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

fun ObservableValue<Boolean>.isEqualTo(other: Boolean): Binding<Boolean> {
    return ComputedSingleBinding(this) { it == other }
}

fun ObservableValue<Boolean>.isNotEqualTo(other: ObservableValue<Boolean>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue != otherValue }
}

fun ObservableValue<Boolean>.isNotEqualTo(other: Boolean): Binding<Boolean> {
    return ComputedSingleBinding(this) { it != other }
}

fun ObservableValue<Boolean>.asString(): Binding<String> {
    return ComputedSingleBinding(this) { it.toString() }
}
