package org.hexworks.cobalt.datatypes.expression

import org.hexworks.cobalt.datatypes.binding.BooleanBinding
import org.hexworks.cobalt.datatypes.binding.StringBinding
import org.hexworks.cobalt.datatypes.value.ObservableValue


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
