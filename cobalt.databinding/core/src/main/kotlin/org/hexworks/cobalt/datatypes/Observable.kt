package org.hexworks.cobalt.datatypes

import org.hexworks.cobalt.datatypes.event.InvalidationListener

/**
 * An [Observable] is an entity that wraps content and allows to
 * observe the content for invalidations.
 */
interface Observable {

    fun addListener(listener: InvalidationListener)

    fun removeListener(listener: InvalidationListener)

}
