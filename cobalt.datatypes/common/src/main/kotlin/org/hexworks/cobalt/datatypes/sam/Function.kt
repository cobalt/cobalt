package org.hexworks.cobalt.datatypes.sam

interface Function<in T, out R> {

    fun apply(param: T): R
}
