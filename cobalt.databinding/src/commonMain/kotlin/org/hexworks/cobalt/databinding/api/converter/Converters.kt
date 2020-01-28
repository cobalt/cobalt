package org.hexworks.cobalt.databinding.api.converter

fun <S : Any, T : Any> ((S) -> T).toConverter() = object : Converter<S, T> {
    override fun convert(source: S) = invoke(source)
}
