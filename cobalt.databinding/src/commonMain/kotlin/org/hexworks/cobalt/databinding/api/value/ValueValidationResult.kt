package org.hexworks.cobalt.databinding.api.value

sealed class ValueValidationResult

data class ValueValidationFailed(val cause: ValueValidationFailedException) : ValueValidationResult()

object ValueValidationSuccessful : ValueValidationResult()