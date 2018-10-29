package org.hexworks.cobalt.datatypes

interface Function<in T, out R> {

    fun apply(param: T): R
}
