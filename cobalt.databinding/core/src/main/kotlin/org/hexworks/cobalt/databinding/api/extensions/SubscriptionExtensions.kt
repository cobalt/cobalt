package org.hexworks.cobalt.databinding.api.extensions

import org.hexworks.cobalt.events.Subscription

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
