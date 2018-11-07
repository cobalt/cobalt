package org.hexworks.cobalt.datatypes.sam

interface Consumer<in T> {

    fun accept(value: T)
}
