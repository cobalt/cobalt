package org.hexworks.cobalt.events.api

sealed class CancelState(val cancelled: Boolean)

object NotCancelled : CancelState(false)

object CancelledByHand : CancelState(true)

data class CancelledByException(val exception: Exception) : CancelState(true)
