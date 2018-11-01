package org.hexworks.cobalt.databinding.api.converter

/**
 * [Converter] which returns the same object when [Converter.convert] is called.
 */
class IdentityConverter<T: Any> : Converter<T, T> {
    override fun convert(source: T) = source
}
