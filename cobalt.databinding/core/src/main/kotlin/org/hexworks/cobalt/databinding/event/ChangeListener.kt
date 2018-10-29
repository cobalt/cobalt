package org.hexworks.cobalt.databinding.event

interface ChangeListener<in T> {

    fun changed(changeEvent: ChangeEvent<T>)
}
