package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.BooleanBinding
import org.hexworks.cobalt.databinding.api.binding.StringBinding
import org.hexworks.cobalt.databinding.api.value.ObservableValue

fun ObservableValue<Boolean>.and(other: ObservableValue<Boolean>): BooleanBinding {
    TODO()
}

fun ObservableValue<Boolean>.or(other: ObservableValue<Boolean>): BooleanBinding {
    TODO()
}

operator fun ObservableValue<Boolean>.not(): BooleanBinding {
    TODO()
}

fun ObservableValue<Boolean>.isEqualTo(other: ObservableValue<Boolean>): BooleanBinding {
    TODO()
}

fun ObservableValue<Boolean>.isNotEqualTo(other: ObservableValue<Boolean>): BooleanBinding {
    TODO()
}

fun ObservableValue<Boolean>.asString(): StringBinding {
    TODO()
}
