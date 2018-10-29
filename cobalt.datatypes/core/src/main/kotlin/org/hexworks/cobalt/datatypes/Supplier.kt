package org.hexworks.cobalt.datatypes

interface Supplier<out T> {

    fun get(): T
}
