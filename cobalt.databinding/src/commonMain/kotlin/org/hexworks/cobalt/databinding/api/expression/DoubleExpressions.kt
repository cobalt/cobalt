@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Double>.negate(): Binding<Double> {
    return ComputedBinding(this) { -it }
}

fun ObservableValue<Double>.add(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toDouble() }
}

fun ObservableValue<Double>.add(other: Double): Binding<Double> {
    return ComputedBinding(this) { it + other }
}

fun ObservableValue<Double>.add(other: Float): Binding<Double> {
    return add(other.toDouble())
}

fun ObservableValue<Double>.add(other: Int): Binding<Double> {
    return add(other.toDouble())
}

fun ObservableValue<Double>.add(other: Long): Binding<Double> {
    return add(other.toDouble())
}

fun ObservableValue<Double>.subtract(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toDouble() }
}

fun ObservableValue<Double>.subtract(other: Double): Binding<Double> {
    return ComputedBinding(this) { it - other }
}

fun ObservableValue<Double>.subtract(other: Float): Binding<Double> {
    return subtract(other.toDouble())
}

fun ObservableValue<Double>.subtract(other: Long): Binding<Double> {
    return subtract(other.toDouble())
}

fun ObservableValue<Double>.subtract(other: Int): Binding<Double> {
    return subtract(other.toDouble())
}

fun ObservableValue<Double>.multiply(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toDouble() }
}

fun ObservableValue<Double>.multiply(other: Double): Binding<Double> {
    return ComputedBinding(this) { it * other }
}

fun ObservableValue<Double>.multiply(other: Float): Binding<Double> {
    return multiply(other.toDouble())
}

fun ObservableValue<Double>.multiply(other: Long): Binding<Double> {
    return multiply(other.toDouble())
}

fun ObservableValue<Double>.multiply(other: Int): Binding<Double> {
    return multiply(other.toDouble())
}

fun ObservableValue<Double>.divide(other: ObservableValue<Number>): Binding<Double> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toDouble() }
}

fun ObservableValue<Double>.divide(other: Double): Binding<Double> {
    return ComputedBinding(this) { it / other }
}

fun ObservableValue<Double>.divide(other: Float): Binding<Double> {
    return divide(other.toDouble())
}

fun ObservableValue<Double>.divide(other: Long): Binding<Double> {
    return divide(other.toDouble())
}

fun ObservableValue<Double>.divide(other: Int): Binding<Double> {
    return divide(other.toDouble())
}

fun ObservableValue<Double>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toDouble() }
}

fun ObservableValue<Double>.isEqualTo(other: Long): Binding<Boolean> {
    return isEqualTo(other.toDouble())
}

fun ObservableValue<Double>.isEqualTo(other: Int): Binding<Boolean> {
    return isEqualTo(other.toDouble())
}

fun ObservableValue<Double>.isEqualTo(other: Float): Binding<Boolean> {
    return isEqualTo(other.toDouble())
}

fun ObservableValue<Double>.isEqualTo(other: Double): Binding<Boolean> {
    return ComputedBinding(this) { it == other }
}

fun ObservableValue<Double>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toDouble() }
}

fun ObservableValue<Double>.isNotEqualTo(other: Long): Binding<Boolean> {
    return isNotEqualTo(other.toDouble())
}

fun ObservableValue<Double>.isNotEqualTo(other: Double): Binding<Boolean> {
    return ComputedBinding(this) { it != other }
}

fun ObservableValue<Double>.isNotEqualTo(other: Float): Binding<Boolean> {
    return isNotEqualTo(other.toDouble())
}

fun ObservableValue<Double>.isNotEqualTo(other: Int): Binding<Boolean> {
    return isNotEqualTo(other.toDouble())
}

fun ObservableValue<Double>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toDouble() }
}

fun ObservableValue<Double>.greaterThan(other: Double): Binding<Boolean> {
    return ComputedBinding(this) { it > other }
}

fun ObservableValue<Double>.greaterThan(other: Float): Binding<Boolean> {
    return greaterThan(other.toDouble())
}

fun ObservableValue<Double>.greaterThan(other: Long): Binding<Boolean> {
    return greaterThan(other.toDouble())
}

fun ObservableValue<Double>.greaterThan(other: Int): Binding<Boolean> {
    return greaterThan(other.toDouble())
}

fun ObservableValue<Double>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toDouble() }
}

fun ObservableValue<Double>.lessThan(other: Double): Binding<Boolean> {
    return ComputedBinding(this) { it < other }
}

fun ObservableValue<Double>.lessThan(other: Float): Binding<Boolean> {
    return lessThan(other.toDouble())
}

fun ObservableValue<Double>.lessThan(other: Long): Binding<Boolean> {
    return lessThan(other.toDouble())
}

fun ObservableValue<Double>.lessThan(other: Int): Binding<Boolean> {
    return lessThan(other.toDouble())
}

fun ObservableValue<Double>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toDouble() }
}

fun ObservableValue<Double>.greaterThanOrEqualTo(other: Double): Binding<Boolean> {
    return ComputedBinding(this) { it >= other }
}

fun ObservableValue<Double>.greaterThanOrEqualTo(other: Float): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toDouble())
}

fun ObservableValue<Double>.greaterThanOrEqualTo(other: Long): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toDouble())
}

fun ObservableValue<Double>.greaterThanOrEqualTo(other: Int): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toDouble())
}

fun ObservableValue<Double>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toDouble() }
}

fun ObservableValue<Double>.lessThanOrEqualTo(other: Double): Binding<Boolean> {
    return ComputedBinding(this) { it <= other }
}

fun ObservableValue<Double>.lessThanOrEqualTo(other: Float): Binding<Boolean> {
    return lessThanOrEqualTo(other.toDouble())
}

fun ObservableValue<Double>.lessThanOrEqualTo(other: Long): Binding<Boolean> {
    return lessThanOrEqualTo(other.toDouble())
}

fun ObservableValue<Double>.lessThanOrEqualTo(other: Int): Binding<Boolean> {
    return lessThanOrEqualTo(other.toDouble())
}

fun ObservableValue<Double>.asString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
