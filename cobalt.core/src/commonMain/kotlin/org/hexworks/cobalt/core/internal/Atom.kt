package org.hexworks.cobalt.core.internal

import org.hexworks.cobalt.core.internal.internal.DefaultAtom

interface Atom<T : Any> {

    fun get(): T

    fun transform(transformer: (T) -> T)

    companion object {

        fun <T : Any> fromObject(obj: T): Atom<T> = DefaultAtom(obj)
    }
}

fun <T : Any> T.toAtom(): Atom<T> = Atom.fromObject(this)
