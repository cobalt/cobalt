package org.hexworks.cobalt.databinding.api.data

/**
 * The possible dispose states.
 */
sealed class DisposeState(val isDisposed: Boolean)

object NotDisposed : DisposeState(false)

object DisposedByHand : DisposeState(true)

data class DisposedByException(val exception: Exception) : DisposeState(true)
