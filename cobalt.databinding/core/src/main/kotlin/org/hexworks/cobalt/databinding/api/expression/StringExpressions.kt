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

fun ObservableValue<String>.concat(other: Any): Binding<String> {
    return ComputedSingleBinding(this) { it + other }
}

fun ObservableValue<String>.isEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue == otherValue }
}

fun ObservableValue<String>.isEqualTo(other: String): Binding<Boolean> {
    return ComputedSingleBinding(this) { it == other }
}

fun ObservableValue<String>.isNotEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isNotEqualTo(other: String): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isEqualToIgnoreCase(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isEqualToIgnoreCase(other: String): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isNotEqualToIgnoreCase(
        other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isNotEqualToIgnoreCase(other: String): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.greaterThan(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.greaterThan(other: String): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.lessThan(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.lessThan(other: String): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.greaterThanOrEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.greaterThanOrEqualTo(other: String): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.lessThanOrEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.lessThanOrEqualTo(other: String): Binding<Boolean> {
    TODO()
}

// TODO: integer binding
fun ObservableValue<String>.length(): Binding<Int> {
    TODO()
}
