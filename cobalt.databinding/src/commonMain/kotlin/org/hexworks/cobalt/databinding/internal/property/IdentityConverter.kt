package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter

class IdentityConverter<T : Any> : IsomorphicConverter<T, T> {

    override fun convert(source: T) = source

    override fun convertBack(target: T) = target
}
