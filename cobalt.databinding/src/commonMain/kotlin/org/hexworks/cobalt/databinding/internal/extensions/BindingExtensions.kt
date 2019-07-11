package org.hexworks.cobalt.databinding.internal.extensions

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposedByException

internal fun <T: Any> Binding<T>.runWithDisposeOnFailure(fn: () -> Unit) {
    try {
        fn()
    } catch (e: Exception) {
        dispose(DisposedByException(e))
    }
}
