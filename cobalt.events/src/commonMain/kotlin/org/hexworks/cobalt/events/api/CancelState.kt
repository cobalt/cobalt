package org.hexworks.cobalt.events.api

/**
 * The cancellation state of a cancellable entity.
 * The state can be
 * - not cancelled
 * - cancelled by hand
 * - cancelled by exception
 */
sealed class CancelState(val cancelled: Boolean)

object NotCancelled : CancelState(false) {
    override fun toString() = "NotCancelled"
}

object CancelledByHand : CancelState(true) {
    override fun toString() = "CancelledByHand"
}

data class CancelledByException(val exception: Exception) : CancelState(true)
