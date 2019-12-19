package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.value.ObservableValue

interface ObservableList<T : Any> : List<T>, ObservableValue<List<T>>
