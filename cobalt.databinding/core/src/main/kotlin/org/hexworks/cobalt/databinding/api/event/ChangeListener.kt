package org.hexworks.cobalt.databinding.api.event

interface ChangeListener<in T : Any> {

    fun changed(changeEvent: ChangeEvent<T>)
}
