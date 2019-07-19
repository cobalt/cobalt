package org.hexworks.cobalt

import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE

/**
 * Marker annotation for all things which are in **beta**.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(TYPE, CLASS)
annotation class Beta
