package org.hexworks.cobalt.databinding.internal.expression

import org.hexworks.cobalt.databinding.api.expression.isEmpty
import org.hexworks.cobalt.databinding.api.expression.isNotEmpty
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName")
class StringExpressionsTest {

    @Test
    fun When_property_is_empty_binding_should_have_true_value() {
        val prop = DefaultProperty("")

        val binding = prop.isEmpty()

        assertEquals(expected = true, actual = binding.value)
    }

    @Test
    fun When_property_is_no_longer_empty_binding_should_have_false_value() {
        val prop = DefaultProperty("")

        val binding = prop.isEmpty()

        prop.value = "foo"

        assertEquals(expected = false, actual = binding.value)
    }

    @Test
    fun When_property_is_not_empty_binding_should_have_true_value() {
        val prop = DefaultProperty("")

        val binding = prop.isNotEmpty()

        assertEquals(expected = false, actual = binding.value)
    }

    @Test
    fun When_property_is_empty_binding_should_have_false_value() {
        val prop = DefaultProperty("")

        val binding = prop.isNotEmpty()

        prop.value = "foo"

        assertEquals(expected = true, actual = binding.value)
    }

}
