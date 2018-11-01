package org.hexworks.cobalt.databinding.api.expression

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.ComputedBiBinding
import org.hexworks.cobalt.databinding.internal.binding.ComputedSingleBinding

fun ObservableValue<Long>.negate(): Binding<Long> {
    return ComputedSingleBinding(this) { -it }
}

fun ObservableValue<Long>.add(other: ObservableValue<Number>): Binding<Long> {
    return ComputedBiBinding(this, other) { thisValue, otherValue -> thisValue + otherValue.toLong() }
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
    return ComputedSingleBinding(this) {it + other}
}

fun ObservableValue<Long>.subtract(other: ObservableValue<Number>): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.subtract(other: Double): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.subtract(other: Float): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.subtract(other: Long): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.subtract(other: Int): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.multiply(other: ObservableValue<Number>): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.multiply(other: Double): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.multiply(other: Float): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.multiply(other: Long): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.multiply(other: Int): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.divide(other: ObservableValue<Number>): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.divide(other: Double): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.divide(other: Float): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.divide(other: Long): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.divide(other: Int): Binding<Long> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: ObservableValue<Number>, epsilon: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: Double, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: Float, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: Long): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: Long, epsilon: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: Int): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isEqualTo(other: Int, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: ObservableValue<Number>, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: Double, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: Float, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: Long): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: Long, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: Int): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.isNotEqualTo(other: Int, tolerance: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThan(other: ObservableValue<Number>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThan(other: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThan(other: Float): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThan(other: Long): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThan(other: Int): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThan(other: ObservableValue<Number>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThan(other: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThan(other: Float): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThan(other: Long): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThan(other: Int): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Float): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Long): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.greaterThanOrEqualTo(other: Int): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: ObservableValue<Number>): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Double): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Float): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Long): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.lessThanOrEqualTo(other: Int): Binding<Boolean> {
    TODO()
}

fun ObservableValue<Long>.asString(): Binding<String> {
    TODO()
}

fun ObservableValue<Long>.asString(format: String): Binding<String> {
    TODO()
}
