package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.expression.add
import org.hexworks.cobalt.databinding.api.expression.negate
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName")
class NumberExpressionsTest {

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

        val binding = prop.add(otherProp)

        assertEquals(expected = 6, actual = binding.value)
    }
}
