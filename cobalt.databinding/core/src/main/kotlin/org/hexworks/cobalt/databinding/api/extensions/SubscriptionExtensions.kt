package org.hexworks.cobalt.databinding.api.extensions

import org.hexworks.cobalt.events.api.Subscription

/**
 * Clears the [Subscription]s in this [MutableList] and also
 * clears this list.
 */
fun <T : Subscription> MutableList<T>.clearSubscriptions() {
    forEach {
        try {
            it.cancel()
        } catch (e: Exception) {
            println("Cancelling subscription failed: ${e.message}.")
        }
    }
    clear()
}
