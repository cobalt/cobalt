package org.hexworks.cobalt.datatypes

interface Consumer<in T> {

    fun accept(value: T)
}
