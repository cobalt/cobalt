package org.hexworks.cobalt.databinding.api.data

/**
 * Sealed class holding the possible dispose states.
 */
sealed class DisposeState(val disposed: Boolean)

object NotDisposed : DisposeState(false)

object DisposedByHand : DisposeState(true)

data class DisposedByException(val exception: Exception) : DisposeState(true)
