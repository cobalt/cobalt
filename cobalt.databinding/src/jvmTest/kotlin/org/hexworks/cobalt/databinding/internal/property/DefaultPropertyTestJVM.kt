package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.datatypes.Maybe
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("FunctionName", "TestFunctionName")
class DefaultPropertyTestJVM {

    private val target = DefaultProperty(XUL)

    @Test
    fun When_target_property_value_changes_the_change_listener_should_be_notified_with_the_proper_event() {
        var change = Maybe.empty<ObservableValueChanged<String>>()
        val expectedChange = ObservableValueChanged(
                observableValue = target,
                oldValue = XUL,
                newValue = QUX,
                emitter = target)

        target.onChange {
            change = Maybe.of(it)
        }
        target.value = QUX

        assertTrue(change.isPresent, "No change happened.")
        assertEquals(expected = expectedChange, actual = change.get(), message = "Actual ChangeEvent is different from expected.")

    }

    @Test
    fun When_target_property_is_bound_to_another_one_its_value_should_be_set_to_the_value_of_the_other_property() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        assertEquals(expected = QUX, actual = target.value, message = "Property value was not set to other value.")
    }

    @Test
    fun When_the_value_of_other_property_changes_a_bound_property_value_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = target.value, message = "Property value was not set to other value.")
    }

    @Test
    fun When_the_value_of_other_property_changes_all_bound_property_values_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)
        val boundProperty = DefaultProperty(QUX)

        target.bind(otherProperty)
        boundProperty.bind(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = target.value, message = "Property value was not set to other value.")
        assertEquals(expected = BAZ, actual = boundProperty.value, message = "Bound property value was not set to other value.")

    }

    @Test
    fun When_creating_a_circular_binding_it_should_not_lead_to_stack_overflow() {
        val otherProperty0 = DefaultProperty(QUX)
        val otherProperty1 = DefaultProperty(BAZ)

        target.bind(otherProperty0)
        otherProperty0.bind(otherProperty1)
        otherProperty1.bind(target)

        assertEquals(expected = BAZ, actual = target.value)
        assertEquals(expected = BAZ, actual = otherProperty0.value)
        assertEquals(expected = BAZ, actual = otherProperty1.value)
    }

    @Test
    fun When_setting_a_value_in_a_circular_binding_it_should_not_lead_to_a_deadlock() {
        val otherProperty0 = DefaultProperty(QUX)
        val otherProperty1 = DefaultProperty(BAZ)

        target.bind(otherProperty0)
        otherProperty0.bind(otherProperty1)
        otherProperty1.bind(target)
        target.value = XUL

        assertEquals(expected = XUL, actual = target.value)
        assertEquals(expected = XUL, actual = otherProperty0.value)
        assertEquals(expected = XUL, actual = otherProperty1.value)
    }

    @Test
    fun When_binding_bidirectionally_to_another_property_target_value_should_be_updated() {
        val other = DefaultProperty(QUX)

        target.bind(other)

        assertEquals(expected = QUX, actual = target.value)

    }

    @Test
    fun When_binding_bidirectionally_and_target_value_changes_other_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        target.value = BAZ

        assertEquals(expected = BAZ, actual = otherProperty.value)

    }

    @Test
    fun When_binding_bidirectionally_and_other_value_changes_target_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = target.value)
    }

    @Test
    fun When_binding_bidirectionally_binding_should_have_same_value_as_target() {
        val otherProperty = DefaultProperty(QUX)

        val binding = target.bind(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = binding.value)
    }

    @Test
    fun When_binding_is_disposed_target_should_not_update_when_other_changes() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty).dispose()

        otherProperty.value = BAZ

        assertEquals(expected = QUX, actual = target.value)
    }

    @Test
    fun When_binding_is_disposed_other_should_not_update_when_target_changes() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty).dispose()

        target.value = BAZ

        assertEquals(expected = QUX, actual = otherProperty.value)
    }

    @Test
    fun When_bound_with_converter_target_value_should_be_updated() {
        val otherProperty = DefaultProperty(1)

        target.updateFrom(otherProperty) {
            otherProperty.value.toString()
        }

        assertEquals(expected = ONE, actual = target.value,
                message = "Target value should have been updated.")
    }

    @Test
    fun When_bound_with_converter_and_other_changes_target_should_be_updated() {
        val otherProperty = DefaultProperty(1)

        target.updateFrom(otherProperty) {
            it.toString()
        }

        otherProperty.value = 2

        assertEquals(expected = TWO, actual = target.value,
                message = "Target value should have been updated.")
    }


    companion object {
        const val XUL = "XUL"
        const val QUX = "QUX"
        const val BAZ = "BAZ"

        const val ONE = "1"
        const val TWO = "2"
    }
}
