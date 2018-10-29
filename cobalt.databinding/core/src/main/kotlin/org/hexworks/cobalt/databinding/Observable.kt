package org.hexworks.cobalt.databinding

import org.hexworks.cobalt.databinding.event.InvalidationListener

/**
 * An [Observable] is an entity that wraps content and allows to
 * observe the content for invalidations.
 */
interface Observable {

    fun addListener(listener: InvalidationListener)

    fun removeListener(listener: InvalidationListener)

}
