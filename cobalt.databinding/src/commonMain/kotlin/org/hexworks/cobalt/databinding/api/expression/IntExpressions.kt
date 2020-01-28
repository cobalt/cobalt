@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Int>.negate(): Binding<Int> {
    return ComputedBinding(this) { -it }
}

fun ObservableValue<Int>.add(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toInt() }
}

fun ObservableValue<Int>.add(other: Double): Binding<Int> {
    return add(other.toInt())
}

fun ObservableValue<Int>.add(other: Float): Binding<Int> {
    return add(other.toInt())
}

fun ObservableValue<Int>.add(other: Int): Binding<Int> {
    return ComputedBinding(this) { it + other }
}

fun ObservableValue<Int>.add(other: Long): Binding<Int> {
    return add(other.toInt())
}

fun ObservableValue<Int>.subtract(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toInt() }
}

fun ObservableValue<Int>.subtract(other: Double): Binding<Int> {
    return subtract(other.toInt())
}

fun ObservableValue<Int>.subtract(other: Float): Binding<Int> {
    return subtract(other.toInt())
}

fun ObservableValue<Int>.subtract(other: Long): Binding<Int> {
    return subtract(other.toInt())
}

fun ObservableValue<Int>.subtract(other: Int): Binding<Int> {
    return ComputedBinding(this) { it - other }
}

fun ObservableValue<Int>.multiply(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toInt() }
}

fun ObservableValue<Int>.multiply(other: Double): Binding<Int> {
    return multiply(other.toInt())
}

fun ObservableValue<Int>.multiply(other: Float): Binding<Int> {
    return multiply(other.toInt())
}

fun ObservableValue<Int>.multiply(other: Long): Binding<Int> {
    return multiply(other.toInt())
}

fun ObservableValue<Int>.multiply(other: Int): Binding<Int> {
    return ComputedBinding(this) { it * other }
}

fun ObservableValue<Int>.divide(other: ObservableValue<Number>): Binding<Int> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toInt() }
}

fun ObservableValue<Int>.divide(other: Double): Binding<Int> {
    return divide(other.toInt())
}

fun ObservableValue<Int>.divide(other: Float): Binding<Int> {
    return divide(other.toInt())
}

fun ObservableValue<Int>.divide(other: Long): Binding<Int> {
    return divide(other.toInt())
}

fun ObservableValue<Int>.divide(other: Int): Binding<Int> {
    return ComputedBinding(this) { it / other }
}

fun ObservableValue<Int>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toInt() }
}

fun ObservableValue<Int>.isEqualTo(other: Long): Binding<Boolean> {
    return isEqualTo(other.toInt())
}

fun ObservableValue<Int>.isEqualTo(other: Int): Binding<Boolean> {
    return ComputedBinding(this) { it == other }
}

fun ObservableValue<Int>.isEqualTo(other: Float): Binding<Boolean> {
    return isEqualTo(other.toInt())
}

fun ObservableValue<Int>.isEqualTo(other: Double): Binding<Boolean> {
    return isEqualTo(other.toInt())
}

fun ObservableValue<Int>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toInt() }
}

fun ObservableValue<Int>.isNotEqualTo(other: Long): Binding<Boolean> {
    return isNotEqualTo(other.toInt())
}

fun ObservableValue<Int>.isNotEqualTo(other: Double): Binding<Boolean> {
    return isNotEqualTo(other.toInt())
}

fun ObservableValue<Int>.isNotEqualTo(other: Float): Binding<Boolean> {
    return isNotEqualTo(other.toInt())
}

fun ObservableValue<Int>.isNotEqualTo(other: Int): Binding<Boolean> {
    return ComputedBinding(this) { it != other }
}

fun ObservableValue<Int>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toInt() }
}

fun ObservableValue<Int>.greaterThan(other: Double): Binding<Boolean> {
    return greaterThan(other.toInt())
}

fun ObservableValue<Int>.greaterThan(other: Float): Binding<Boolean> {
    return greaterThan(other.toInt())
}

fun ObservableValue<Int>.greaterThan(other: Long): Binding<Boolean> {
    return greaterThan(other.toInt())
}

fun ObservableValue<Int>.greaterThan(other: Int): Binding<Boolean> {
    return ComputedBinding(this) { it > other }
}

fun ObservableValue<Int>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toInt() }
}

fun ObservableValue<Int>.lessThan(other: Double): Binding<Boolean> {
    return lessThan(other.toInt())
}

fun ObservableValue<Int>.lessThan(other: Float): Binding<Boolean> {
    return lessThan(other.toInt())
}

fun ObservableValue<Int>.lessThan(other: Long): Binding<Boolean> {
    return lessThan(other.toInt())
}

fun ObservableValue<Int>.lessThan(other: Int): Binding<Boolean> {
    return ComputedBinding(this) { it < other }
}

fun ObservableValue<Int>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toInt() }
}

fun ObservableValue<Int>.greaterThanOrEqualTo(other: Double): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toInt())
}

fun ObservableValue<Int>.greaterThanOrEqualTo(other: Float): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toInt())
}

fun ObservableValue<Int>.greaterThanOrEqualTo(other: Long): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toInt())
}

fun ObservableValue<Int>.greaterThanOrEqualTo(other: Int): Binding<Boolean> {
    return ComputedBinding(this) { it >= other }
}

fun ObservableValue<Int>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toInt() }
}

fun ObservableValue<Int>.lessThanOrEqualTo(other: Double): Binding<Boolean> {
    return lessThanOrEqualTo(other.toInt())
}

fun ObservableValue<Int>.lessThanOrEqualTo(other: Float): Binding<Boolean> {
    return lessThanOrEqualTo(other.toInt())
}

fun ObservableValue<Int>.lessThanOrEqualTo(other: Long): Binding<Boolean> {
    return lessThanOrEqualTo(other.toInt())
}

fun ObservableValue<Int>.lessThanOrEqualTo(other: Int): Binding<Boolean> {
    return ComputedBinding(this) { it <= other }
}

fun ObservableValue<Int>.asString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
