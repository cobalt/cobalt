package org.hexworks.cobalt.datatypes.sam

interface Supplier<out T> {

    fun get(): T
}
