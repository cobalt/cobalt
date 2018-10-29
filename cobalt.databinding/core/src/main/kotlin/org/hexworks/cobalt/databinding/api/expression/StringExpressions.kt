package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.binding.BooleanBinding
import org.hexworks.cobalt.databinding.api.binding.StringBinding
import org.hexworks.cobalt.databinding.api.value.ObservableValue

fun ObservableValue<String>.isNull(): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isNotNull(): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isEmpty(): BooleanBinding {
    TODO()
}


fun ObservableValue<String>.isNotEmpty(): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.concat(other: Any): StringBinding {
    TODO()
}

fun ObservableValue<String>.isEqualTo(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isEqualTo(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isNotEqualTo(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isNotEqualTo(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isEqualToIgnoreCase(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isEqualToIgnoreCase(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isNotEqualToIgnoreCase(
        other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.isNotEqualToIgnoreCase(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.greaterThan(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.greaterThan(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.lessThan(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.lessThan(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.greaterThanOrEqualTo(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.greaterThanOrEqualTo(other: String): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.lessThanOrEqualTo(other: ObservableValue<String>): BooleanBinding {
    TODO()
}

fun ObservableValue<String>.lessThanOrEqualTo(other: String): BooleanBinding {
    TODO()
}

// TODO: integer binding
fun ObservableValue<String>.length(): Binding<Int> {
    TODO()
}
