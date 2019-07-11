package org.hexworks.cobalt.sam

interface Consumer<in T> {

    fun accept(value: T)
}
