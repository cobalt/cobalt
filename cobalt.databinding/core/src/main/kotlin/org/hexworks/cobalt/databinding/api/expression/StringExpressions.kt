package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue

fun ObservableValue<String>.isNull(): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isNotNull(): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isEmpty(): Binding<Boolean> {
    TODO()
}


fun ObservableValue<String>.isNotEmpty(): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.concat(other: Any): Binding<String> {
    TODO()
}

fun ObservableValue<String>.isEqualTo(other: ObservableValue<String>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<String>.isEqualTo(other: String): Binding<Boolean> {
    TODO()
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
