package org.hexworks.cobalt.databinding.api.event

interface ChangeListener<in T> {

    fun changed(changeEvent: ChangeEvent<T>)
}
