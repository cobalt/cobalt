package org.hexworks.cobalt.core.internal.internal

import org.hexworks.cobalt.core.internal.Atom
import kotlin.jvm.Synchronized
import kotlin.jvm.Volatile

class DefaultAtom<T : Any>(initialValue: T) : Atom<T> {

    @Volatile
    private var value: T = initialValue

    override fun get(): T = value

    @Synchronized
    override fun transform(transformer: (T) -> T) {
        value = transformer(value)
    }

}