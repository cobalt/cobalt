package org.hexworks.cobalt.databinding.api.converter

interface Converter<S, T> {

    fun convert(source: S): T

    companion object
}
