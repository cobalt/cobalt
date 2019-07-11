package org.hexworks.cobalt.sam

interface Function<in T, out R> {

    fun apply(param: T): R
}
