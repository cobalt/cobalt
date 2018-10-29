package org.hexworks.cobalt.databinding.expression

import org.hexworks.cobalt.databinding.binding.BooleanBinding
import org.hexworks.cobalt.databinding.binding.StringBinding
import org.hexworks.cobalt.databinding.value.ObservableValue


/**
 * Sole constructor
 */
abstract class BooleanExpression : ObservableValue<Boolean> {

    fun and(other: ObservableValue<Boolean>): BooleanBinding {
        TODO()
    }

    fun or(other: ObservableValue<Boolean>): BooleanBinding {
        TODO()
    }

    operator fun not(): BooleanBinding {
        TODO()
    }

    fun isEqualTo(other: ObservableValue<Boolean>): BooleanBinding {
        TODO()
    }

    fun isNotEqualTo(other: ObservableValue<Boolean>): BooleanBinding {
        TODO()
    }

    fun asString(): StringBinding {
        TODO()
    }

    companion object {

        fun booleanExpression(value: ObservableValue<Boolean>): BooleanExpression {
            TODO()
        }
    }
}
