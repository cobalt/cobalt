package org.hexworks.cobalt.core.platform

import kotlin.coroutines.CoroutineContext

actual fun <T : Any> runTest(context: CoroutineContext,
                             block: suspend () -> T) {
    kotlinx.coroutines.runBlocking(context) { block() }
}
