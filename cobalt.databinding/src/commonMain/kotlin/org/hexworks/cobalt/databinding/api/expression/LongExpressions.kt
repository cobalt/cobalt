@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Long>.negate(): Binding<Long> {
    return ComputedBinding(this) { -it }
}

fun ObservableValue<Long>.add(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toLong() }
}

fun ObservableValue<Long>.add(other: Double): Binding<Long> {
    return add(other.toLong())
}

fun ObservableValue<Long>.add(other: Float): Binding<Long> {
    return add(other.toLong())
}

fun ObservableValue<Long>.add(other: Int): Binding<Long> {
    return add(other.toLong())
}

fun ObservableValue<Long>.add(other: Long): Binding<Long> {
    return ComputedBinding(this) { it + other }
}

fun ObservableValue<Long>.subtract(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toLong() }
}

fun ObservableValue<Long>.subtract(other: Double): Binding<Long> {
    return subtract(other.toLong())
}

fun ObservableValue<Long>.subtract(other: Float): Binding<Long> {
    return subtract(other.toLong())
}

fun ObservableValue<Long>.subtract(other: Long): Binding<Long> {
    return ComputedBinding(this) { it - other }
}

fun ObservableValue<Long>.subtract(other: Int): Binding<Long> {
    return subtract(other.toLong())
}

fun ObservableValue<Long>.multiply(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toLong() }
}

fun ObservableValue<Long>.multiply(other: Double): Binding<Long> {
    return multiply(other.toLong())
}

fun ObservableValue<Long>.multiply(other: Float): Binding<Long> {
    return multiply(other.toLong())
}

fun ObservableValue<Long>.multiply(other: Long): Binding<Long> {
    return ComputedBinding(this) { it * other }
}

fun ObservableValue<Long>.multiply(other: Int): Binding<Long> {
    return multiply(other.toLong())
}

fun ObservableValue<Long>.divide(other: ObservableValue<Number>): Binding<Long> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toLong() }
}

fun ObservableValue<Long>.divide(other: Double): Binding<Long> {
    return divide(other.toLong())
}

fun ObservableValue<Long>.divide(other: Float): Binding<Long> {
    return divide(other.toLong())
}

fun ObservableValue<Long>.divide(other: Long): Binding<Long> {
    return ComputedBinding(this) { it / other }
}

fun ObservableValue<Long>.divide(other: Int): Binding<Long> {
    return divide(other.toLong())
}

fun ObservableValue<Long>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toLong() }
}

fun ObservableValue<Long>.isEqualTo(other: Long): Binding<Boolean> {
    return ComputedBinding(this) { it == other }
}

fun ObservableValue<Long>.isEqualTo(other: Int): Binding<Boolean> {
    return isEqualTo(other.toLong())
}

fun ObservableValue<Long>.isEqualTo(other: Float): Binding<Boolean> {
    return isEqualTo(other.toLong())
}

fun ObservableValue<Long>.isEqualTo(other: Double): Binding<Boolean> {
    return isEqualTo(other.toLong())
}

fun ObservableValue<Long>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toLong() }
}

fun ObservableValue<Long>.isNotEqualTo(other: Long): Binding<Boolean> {
    return ComputedBinding(this) { it != other }
}

fun ObservableValue<Long>.isNotEqualTo(other: Double): Binding<Boolean> {
    return isNotEqualTo(other.toLong())
}

fun ObservableValue<Long>.isNotEqualTo(other: Float): Binding<Boolean> {
    return isNotEqualTo(other.toLong())
}

fun ObservableValue<Long>.isNotEqualTo(other: Int): Binding<Boolean> {
    return isNotEqualTo(other.toLong())
}

fun ObservableValue<Long>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toLong() }
}

fun ObservableValue<Long>.greaterThan(other: Double): Binding<Boolean> {
    return greaterThan(other.toLong())
}

fun ObservableValue<Long>.greaterThan(other: Float): Binding<Boolean> {
    return greaterThan(other.toLong())
}

fun ObservableValue<Long>.greaterThan(other: Long): Binding<Boolean> {
    return ComputedBinding(this) { it > other }
}

fun ObservableValue<Long>.greaterThan(other: Int): Binding<Boolean> {
    return greaterThan(other.toLong())
}

fun ObservableValue<Long>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toLong() }
}

fun ObservableValue<Long>.lessThan(other: Double): Binding<Boolean> {
    return lessThan(other.toLong())
}

fun ObservableValue<Long>.lessThan(other: Float): Binding<Boolean> {
    return lessThan(other.toLong())
}

fun ObservableValue<Long>.lessThan(other: Long): Binding<Boolean> {
    return ComputedBinding(this) { it < other }
}

fun ObservableValue<Long>.lessThan(other: Int): Binding<Boolean> {
    return lessThan(other.toLong())
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toLong() }
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Double): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toLong())
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Float): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toLong())
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Long): Binding<Boolean> {
    return ComputedBinding(this) { it >= other }
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Int): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toLong())
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toLong() }
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Double): Binding<Boolean> {
    return lessThanOrEqualTo(other.toLong())
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Float): Binding<Boolean> {
    return lessThanOrEqualTo(other.toLong())
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Long): Binding<Boolean> {
    return ComputedBinding(this) { it <= other }
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Int): Binding<Boolean> {
    return lessThanOrEqualTo(other.toLong())
}

fun ObservableValue<Long>.asString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
