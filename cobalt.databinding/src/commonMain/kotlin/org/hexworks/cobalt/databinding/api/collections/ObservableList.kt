package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * An [ObservableList] will fire [ObservableValueChanged] events whenever
 * the underlying [List] changes. Note that both [ObservableValueChanged.oldValue]
 * and [ObservableValueChanged.newValue] is considered a stable snapshot, eg:
 * it won't change if the underlying [List] changes *after* receiving the event.
 */
interface ObservableList<T : Any> : List<T>, ObservableValue<List<T>> {

    companion object
}
