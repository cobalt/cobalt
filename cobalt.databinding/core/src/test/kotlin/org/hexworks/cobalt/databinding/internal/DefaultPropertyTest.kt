package org.hexworks.cobalt.databinding.internal

import org.hexworks.cobalt.databinding.api.event.ChangeEvent
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
    fun When_the_property_value_changes_the_change_listener_should_be_notified_with_the_proper_event() {
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
    fun When_a_property_is_bound_to_another_one_its_value_should_be_set_to_the_value_of_the_other_property() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        assertEquals(expected = QUX, actual = target.value, message = "Property value was not set to other value.")
    }

    @Test
    fun When_the_value_of_a_property_changes_a_bound_property_value_should_be_updated() {
        val otherProperty = DefaultProperty(QUX)

        target.bind(otherProperty)

        otherProperty.value = BAZ

        assertEquals(expected = BAZ, actual = target.value, message = "Property value was not set to other value.")
    }

    @Test
    fun When_the_value_of_a_property_changes_all_bound_property_values_should_be_updated() {
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

    companion object {
        const val XUL = "XUL"
        const val QUX = "QUX"
        const val BAZ = "BAZ"
    }
}
