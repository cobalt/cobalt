package org.hexworks.cobalt.core.internal

import org.hexworks.cobalt.core.platform.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AtomTest {

    @Test
    fun test() {
        val ref = 1.toAtom()

        runTest {
            ref.transform {
                2
            }
            assertEquals(2, ref.get())
        }
    }
}