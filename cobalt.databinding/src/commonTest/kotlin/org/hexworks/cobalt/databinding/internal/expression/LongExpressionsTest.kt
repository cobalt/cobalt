package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.expression.add
import org.hexworks.cobalt.databinding.api.expression.multiply
import org.hexworks.cobalt.databinding.api.expression.negate
import org.hexworks.cobalt.databinding.api.expression.subtract
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName")
class LongExpressionsTest {

    @Test
    fun When_property_is_negated_binding_value_should_be_negative() {
        val prop: DefaultProperty<Long> = DefaultProperty(2)

        val binding = prop.negate()

        assertEquals(expected = -2, actual = binding.value)
    }

    @Test
    fun When_property_is_added_to_other_property_binding_value_should_be_their_sum() {
        val prop: DefaultProperty<Long> = DefaultProperty(2)
        val otherProp: DefaultProperty<Long> = DefaultProperty(4)

        val sum = prop.add(otherProp)

        assertEquals(expected = 6, actual = sum.value)
    }

    @Test
    fun When_sum_binding_is_created_and_property_changes_sum_should_also_change() {
        val prop: DefaultProperty<Long> = DefaultProperty(2)
        val otherProp: DefaultProperty<Long> = DefaultProperty(4)

        val sum = prop.add(otherProp)

        prop.value = 5

        assertEquals(expected = 9, actual = sum.value)
    }

    @Test
    fun When_complex_binding_is_created_and_property_changes_binding_should_also_change() {
        val sumPart0: DefaultProperty<Long> = DefaultProperty(2)
        val sumPart1: DefaultProperty<Long> = DefaultProperty(4)

        val diffPart0: DefaultProperty<Long> = DefaultProperty(8)
        val diffPart1: DefaultProperty<Long> = DefaultProperty(2)

        val sum = sumPart0.add(sumPart1)
        val diff = diffPart0.subtract(diffPart1)

        val complexBinding = sum.multiply(diff)

        assertEquals(expected = 36, actual = complexBinding.value)

        diffPart1.value = 7

        assertEquals(expected = 6, actual = complexBinding.value)
    }
}
