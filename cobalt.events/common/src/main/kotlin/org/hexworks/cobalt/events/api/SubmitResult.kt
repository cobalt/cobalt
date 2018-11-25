package org.hexworks.cobalt.events.api

sealed class SubmitResult<out T: Any>(val successful: Boolean) {

    abstract operator fun component1(): T
}

object NoProcessorSubmitResult : SubmitResult<Nothing>(false) {
    override fun component1() = throw IllegalStateException("No processor found for the given event and result type.")
}

class SubmitResultValue<out T: Any>(val value: T) : SubmitResult<T>(true) {

    override fun component1() = value
}

class SubmitResultError(val exception: Exception): SubmitResult<Nothing>(false) {
    override fun component1() = throw exception
}
