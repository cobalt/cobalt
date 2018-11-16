package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.value.ObservableValue

interface ObservableList<E> : List<E>, ObservableValue<List<E>>
