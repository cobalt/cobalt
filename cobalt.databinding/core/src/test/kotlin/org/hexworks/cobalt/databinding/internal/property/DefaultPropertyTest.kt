package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.extensions.bind
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.datatypes.Maybe
import kotlin.test.*

@Suppress("FunctionName", "TestFunctionName")
class DefaultPropertyTest {

    lateinit var target: DefaultProperty<String>

    @BeforeTest
    fun Set_up() {
        target = DefaultProperty(XUL)
    }

    @Test
    fun When_target_property_value_changes_the_change_listener_should_be_notified_with_the_proper_event() {
        var change = Maybe.empty<ChangeEvent<String>>()
        val expectedChange = ChangeEvent(target, XUL, QUX)

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
        assertEquals(expected = BAZ, actual = boundProperty.value, message = "Property value was not set to other value.")

    }

    @Test
    fun Should_not_allow_setting_the_value_of_a_bound_property() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        assertFailsWith<IllegalArgumentException> {
            target.value = BAZ
        }
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
    fun When_binding_bidirectionally_to_another_property_target_value_should_be_updated() {
        val otherProperty0 = DefaultProperty(QUX)

        target.bindBidirectional(otherProperty0)

        assertEquals(expected = QUX, actual = target.value)

    }

    @Test
    fun When_binding_bidirectionally_and_target_value_changes_other_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)

        target.bindBidirectional(otherProperty)

        target.value = BAZ

        assertEquals(expected = BAZ, actual = otherProperty.value)

    }

    @Test
    fun When_binding_bidirectionally_and_other_value_changes_target_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)

        target.bindBidirectional(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = target.value)
    }

    @Test
    fun When_binding_bidirectionally_binding_should_have_same_value_as_target() {
        val otherProperty = DefaultProperty(QUX)

        val binding = target.bindBidirectional(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = binding.value)
    }

    @Test
    fun When_binding_is_disposed_target_should_not_update_when_other_changes() {
        val otherProperty = DefaultProperty(QUX)

        target.bindBidirectional(otherProperty).dispose()

        otherProperty.value = BAZ

        assertEquals(expected = QUX, actual = target.value)
    }

    @Test
    fun When_binding_is_disposed_other_should_not_update_when_target_changes() {
        val otherProperty = DefaultProperty(QUX)

        target.bindBidirectional(otherProperty).dispose()

        target.value = BAZ

        assertEquals(expected = QUX, actual = otherProperty.value)
    }

    @Test
    fun When_bound_with_converter_target_value_should_be_updated() {
        val otherProperty = DefaultProperty(1)

        target.bind(otherProperty) {
            otherProperty.value.toString()
        }

        assertEquals(expected = ONE, actual = target.value,
                message = "Target value should have been updated.")
    }

    @Test
    fun When_bound_with_converter_and_other_changes_target_should_be_updated() {
        val otherProperty = DefaultProperty(1)

        target.bind(otherProperty) {
            otherProperty.value.toString()
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
