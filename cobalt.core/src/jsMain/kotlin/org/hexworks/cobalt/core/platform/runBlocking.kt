package org.hexworks.cobalt.core.platform

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.coroutines.CoroutineContext

actual fun <T: Any> runTest(context: CoroutineContext, block: suspend () -> T) {
    GlobalScope.promise(context) {
        block()
    }
}
