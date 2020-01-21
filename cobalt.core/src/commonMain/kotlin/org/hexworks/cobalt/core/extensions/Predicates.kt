package org.hexworks.cobalt.core.extensions

typealias Predicate<T> = Function1<T, Boolean>

infix fun <T> Predicate<T>.and(other: Predicate<in T>): Predicate<T> = {
    this(it) && other(it)
}

fun <T> Predicate<T>.negate(): Predicate<T> = {
    !this(it)
}

infix fun <T> Predicate<T>.or(other: Predicate<in T>): Predicate<T> = {
    this(it) || other(it)
}
