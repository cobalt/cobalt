package org.hexworks.cobalt.core.platform

import kotlinx.coroutines.GlobalScope
import kotlin.coroutines.CoroutineContext

expect fun <T: Any> runTest(
        context: CoroutineContext = GlobalScope.coroutineContext,
        block: suspend () -> T)
