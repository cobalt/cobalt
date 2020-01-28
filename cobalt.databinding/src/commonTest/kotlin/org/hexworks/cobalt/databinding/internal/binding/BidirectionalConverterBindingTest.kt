package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.data.DisposedByException
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName", "FunctionName")
class BidirectionalConverterBindingTest {

    lateinit var target: DefaultProperty<String>

    @BeforeTest
    fun Set_up() {
        target = DefaultProperty(ONE)
    }

    @Test
    fun When_target_property_is_bound_bidirectionally_to_other_property_with_different_type_its_value_should_be_updated() {
        val otherProperty = DefaultProperty(2)

        bindTargetToOther(otherProperty)

        assertEquals(expected = TWO, actual = target.value)
    }

    @Test
    fun When_target_property_is_bound_bidirectionally_to_other_property_and_other_property_is_updated_target_should_get_updated() {
        val otherProperty = DefaultProperty(2)

        bindTargetToOther(otherProperty)

        otherProperty.value = 1

        assertEquals(expected = ONE, actual = target.value)
    }

    @Test
    fun When_target_property_is_bound_bidirectionally_to_other_property_and_target_property_is_updated_other_should_get_updated() {
        val otherProperty = DefaultProperty(2)

        bindTargetToOther(otherProperty)

        target.value = ONE

        assertEquals(expected = 1, actual = otherProperty.value)
    }

    @Test
    fun When_target_property_is_bound_bidirectionally_to_other_property_and_target_property_is_updated_with_exception_binding_should_be_disposed() {
        val otherProperty = DefaultProperty(2)

        val binding = bindTargetToOther(otherProperty)

        target.value = FOO

        assertEquals(expected = DisposedByException::class,
                actual = binding.disposeState::class,
                message = "Binding should have been disposed because of exception")
    }

    private fun bindTargetToOther(otherProperty: DefaultProperty<Int>): Binding<String> {
        return target.bind(other = otherProperty, converter = object : IsomorphicConverter<Int, String> {

            override fun convertBack(target: String) = target.toInt()

            override fun convert(source: Int) = source.toString()

        })
    }

    companion object {

        const val FOO = "foo"
        const val ONE = "1"
        const val TWO = "2"
    }

}
