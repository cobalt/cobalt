package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.*

@Suppress("FunctionName", "TestFunctionName")
class ComputedDualBindingTest {

    private lateinit var target: ComputedDualBinding<String, Int, Boolean>

    private val stringValue = DefaultProperty(ONE)
    private val intValue = DefaultProperty(1)

    @BeforeTest
    fun Set_up() {
        target = ComputedDualBinding(stringValue, intValue) { str, int ->
            str.toInt() == int
        }
    }

    @Test
    fun When_creating_a_computed_binding_its_initial_value_should_be_calculated_properly() {
        assertEquals(expected = true, actual = target.value)
    }

    @Test
    fun When_string_value_changes_to_a_value_which_is_not_equal_to_int_value_binding_value_should_change_to_false() {
        stringValue.value = TWO

        assertEquals(expected = false, actual = target.value)
    }

    @Test
    fun When_int_value_changes_to_a_value_which_is_not_equal_to_string_value_binding_value_should_change_to_false() {
        intValue.value = 2

        assertEquals(expected = false, actual = target.value)
    }

    @Test
    fun When_int_value_changes_back_to_be_equal_with_string_value_binding_should_be_updated_to_true() {
        intValue.value = 2
        intValue.value = 1

        assertEquals(expected = true, actual = target.value)
    }

    @Test
    fun When_binding_is_disposed_it_should_throw_exception_when_value_is_accessed() {
        target.dispose()

        assertFailsWith<IllegalArgumentException> {
            target.value
        }
    }

    @Test
    fun When_int_value_changes_on_change_subscribers_should_be_notified() {
        var notified = false

        target.onChange {
            notified = true
        }

        intValue.value = 4

        assertTrue(notified, "No notification happened.")
    }

    @Test
    fun When_string_value_changes_on_change_subscribers_should_be_notified() {
        var notified = false

        target.onChange {
            notified = true
        }

        stringValue.value = TWO

        assertTrue(notified, "No notification happened.")
    }

    @Test
    fun When_value_does_not_change_on_change_subscribers_shouldnt_be_notified() {
        var notified = false

        target.onChange {
            notified = true
        }

        stringValue.value = ONE

        assertFalse(notified, "No notification should have happened.")
    }

    companion object {

        const val ONE = "1"
        const val TWO = "2"
    }
}
