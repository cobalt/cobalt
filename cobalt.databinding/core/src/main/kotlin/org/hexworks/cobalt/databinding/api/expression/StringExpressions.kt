package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBiBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedSingleBinding

fun ObservableValue<String>.isEmpty(): Binding<Boolean> {
    return ComputedSingleBinding(this) { it.isEmpty() }
}

fun ObservableValue<String>.isNotEmpty(): Binding<Boolean> {
    return ComputedSingleBinding(this) { it.isNotEmpty() }
}

fun ObservableValue<String>.concat(other: ObservableValue<String>): Binding<String> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue + otherValue }
}

fun ObservableValue<String>.concat(other: Any): Binding<String> {
    return ComputedSingleBinding(this) { it + other.toString() }
}

fun ObservableValue<String>.isEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

fun ObservableValue<String>.isEqualTo(other: String): Binding<Boolean> {
    return ComputedSingleBinding(this) { it == other }
}

fun ObservableValue<String>.isNotEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue != otherValue }
}

fun ObservableValue<String>.isNotEqualTo(other: String): Binding<Boolean> {
    return ComputedSingleBinding(this) { it != other }
}

fun ObservableValue<String>.isEqualToIgnoreCase(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue.toLowerCase() == otherValue.toLowerCase() }
}

fun ObservableValue<String>.isEqualToIgnoreCase(other: String): Binding<Boolean> {
    return ComputedSingleBinding(this) { it.toLowerCase() == other.toLowerCase() }
}

fun ObservableValue<String>.isNotEqualToIgnoreCase(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue.toLowerCase() != otherValue.toLowerCase() }
}

fun ObservableValue<String>.isNotEqualToIgnoreCase(other: String): Binding<Boolean> {
    return ComputedSingleBinding(this) { it.toLowerCase() != other.toLowerCase() }
}

fun ObservableValue<String>.length(): Binding<Int> {
    return ComputedSingleBinding(this) { it.length }
}
