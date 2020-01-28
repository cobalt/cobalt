@file:Suppress("unused")

package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedDualBinding

fun ObservableValue<Float>.negate(): Binding<Float> {
    return ComputedBinding(this) { -it }
}

fun ObservableValue<Float>.add(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toFloat() }
}

fun ObservableValue<Float>.add(other: Double): Binding<Float> {
    return add(other.toFloat())
}

fun ObservableValue<Float>.add(other: Float): Binding<Float> {
    return ComputedBinding(this) { it + other }
}

fun ObservableValue<Float>.add(other: Int): Binding<Float> {
    return add(other.toFloat())
}

fun ObservableValue<Float>.add(other: Long): Binding<Float> {
    return add(other.toFloat())
}

fun ObservableValue<Float>.subtract(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue - otherValue.toFloat() }
}

fun ObservableValue<Float>.subtract(other: Double): Binding<Float> {
    return subtract(other.toFloat())
}

fun ObservableValue<Float>.subtract(other: Float): Binding<Float> {
    return ComputedBinding(this) { it - other }
}

fun ObservableValue<Float>.subtract(other: Long): Binding<Float> {
    return subtract(other.toFloat())
}

fun ObservableValue<Float>.subtract(other: Int): Binding<Float> {
    return subtract(other.toFloat())
}

fun ObservableValue<Float>.multiply(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue * otherValue.toFloat() }
}

fun ObservableValue<Float>.multiply(other: Double): Binding<Float> {
    return multiply(other.toFloat())
}

fun ObservableValue<Float>.multiply(other: Float): Binding<Float> {
    return ComputedBinding(this) { it * other }
}

fun ObservableValue<Float>.multiply(other: Long): Binding<Float> {
    return multiply(other.toFloat())
}

fun ObservableValue<Float>.multiply(other: Int): Binding<Float> {
    return multiply(other.toFloat())
}

fun ObservableValue<Float>.divide(other: ObservableValue<Number>): Binding<Float> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue / otherValue.toFloat() }
}

fun ObservableValue<Float>.divide(other: Double): Binding<Float> {
    return divide(other.toFloat())
}

fun ObservableValue<Float>.divide(other: Float): Binding<Float> {
    return ComputedBinding(this) { it / other }
}

fun ObservableValue<Float>.divide(other: Long): Binding<Float> {
    return divide(other.toFloat())
}

fun ObservableValue<Float>.divide(other: Int): Binding<Float> {
    return divide(other.toFloat())
}

fun ObservableValue<Float>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue == otherValue.toFloat() }
}

fun ObservableValue<Float>.isEqualTo(other: Long): Binding<Boolean> {
    return isEqualTo(other.toFloat())
}

fun ObservableValue<Float>.isEqualTo(other: Int): Binding<Boolean> {
    return isEqualTo(other.toFloat())
}

fun ObservableValue<Float>.isEqualTo(other: Float): Binding<Boolean> {
    return ComputedBinding(this) { it == other }
}

fun ObservableValue<Float>.isEqualTo(other: Double): Binding<Boolean> {
    return isEqualTo(other.toFloat())
}

fun ObservableValue<Float>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue != otherValue.toFloat() }
}

fun ObservableValue<Float>.isNotEqualTo(other: Long): Binding<Boolean> {
    return isNotEqualTo(other.toFloat())
}

fun ObservableValue<Float>.isNotEqualTo(other: Double): Binding<Boolean> {
    return isNotEqualTo(other.toFloat())
}

fun ObservableValue<Float>.isNotEqualTo(other: Float): Binding<Boolean> {
    return ComputedBinding(this) { it != other }
}

fun ObservableValue<Float>.isNotEqualTo(other: Int): Binding<Boolean> {
    return isNotEqualTo(other.toFloat())
}

fun ObservableValue<Float>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue > otherValue.toFloat() }
}

fun ObservableValue<Float>.greaterThan(other: Double): Binding<Boolean> {
    return greaterThan(other.toFloat())
}

fun ObservableValue<Float>.greaterThan(other: Float): Binding<Boolean> {
    return ComputedBinding(this) { it > other }
}

fun ObservableValue<Float>.greaterThan(other: Long): Binding<Boolean> {
    return greaterThan(other.toFloat())
}

fun ObservableValue<Float>.greaterThan(other: Int): Binding<Boolean> {
    return greaterThan(other.toFloat())
}

fun ObservableValue<Float>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue < otherValue.toFloat() }
}

fun ObservableValue<Float>.lessThan(other: Double): Binding<Boolean> {
    return lessThan(other.toFloat())
}

fun ObservableValue<Float>.lessThan(other: Float): Binding<Boolean> {
    return ComputedBinding(this) { it < other }
}

fun ObservableValue<Float>.lessThan(other: Long): Binding<Boolean> {
    return lessThan(other.toFloat())
}

fun ObservableValue<Float>.lessThan(other: Int): Binding<Boolean> {
    return lessThan(other.toFloat())
}

fun ObservableValue<Float>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue >= otherValue.toFloat() }
}

fun ObservableValue<Float>.greaterThanOrEqualTo(other: Double): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toFloat())
}

fun ObservableValue<Float>.greaterThanOrEqualTo(other: Float): Binding<Boolean> {
    return ComputedBinding(this) { it >= other }
}

fun ObservableValue<Float>.greaterThanOrEqualTo(other: Long): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toFloat())
}

fun ObservableValue<Float>.greaterThanOrEqualTo(other: Int): Binding<Boolean> {
    return greaterThanOrEqualTo(other.toFloat())
}

fun ObservableValue<Float>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    return ComputedDualBinding(this, other) { thisValue, otherValue -> thisValue <= otherValue.toFloat() }
}

fun ObservableValue<Float>.lessThanOrEqualTo(other: Double): Binding<Boolean> {
    return lessThanOrEqualTo(other.toFloat())
}

fun ObservableValue<Float>.lessThanOrEqualTo(other: Float): Binding<Boolean> {
    return ComputedBinding(this) { it <= other }
}

fun ObservableValue<Float>.lessThanOrEqualTo(other: Long): Binding<Boolean> {
    return lessThanOrEqualTo(other.toFloat())
}

fun ObservableValue<Float>.lessThanOrEqualTo(other: Int): Binding<Boolean> {
    return lessThanOrEqualTo(other.toFloat())
}

fun ObservableValue<Float>.asString(): Binding<String> {
    return ComputedBinding(this) { it.toString() }
}
