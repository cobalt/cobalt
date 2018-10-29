package org.hexworks.cobalt.datatypes.expression

import org.hexworks.cobalt.datatypes.binding.Binding
import org.hexworks.cobalt.datatypes.binding.BooleanBinding
import org.hexworks.cobalt.datatypes.value.ObservableValue

abstract class StringExpression : ObservableValue<String> {

    fun isNull(): BooleanBinding {
        TODO()
    }

    fun isNotNull(): BooleanBinding {
        TODO()
    }

    fun isEmpty(): BooleanBinding {
        TODO()
    }


    fun isNotEmpty(): BooleanBinding {
        TODO()
    }

    fun concat(other: Any): StringExpression {
        TODO()
    }

    fun isEqualTo(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun isEqualTo(other: String): BooleanBinding {
        TODO()
    }

    fun isNotEqualTo(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun isNotEqualTo(other: String): BooleanBinding {
        TODO()
    }

    fun isEqualToIgnoreCase(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun isEqualToIgnoreCase(other: String): BooleanBinding {
        TODO()
    }

    fun isNotEqualToIgnoreCase(
            other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun isNotEqualToIgnoreCase(other: String): BooleanBinding {
        TODO()
    }

    fun greaterThan(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun greaterThan(other: String): BooleanBinding {
        TODO()
    }

    fun lessThan(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun lessThan(other: String): BooleanBinding {
        TODO()
    }

    fun greaterThanOrEqualTo(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun greaterThanOrEqualTo(other: String): BooleanBinding {
        TODO()
    }

    fun lessThanOrEqualTo(other: ObservableValue<String>): BooleanBinding {
        TODO()
    }

    fun lessThanOrEqualTo(other: String): BooleanBinding {
        TODO()
    }

    // TODO: integer binding
    fun length(): Binding<Int> {
        TODO()
    }

    companion object {

        fun stringExpression(
                value: ObservableValue<*>): StringExpression {
            TODO()
        }
    }
}
