package org.hexworks.cobalt.datatypes.event

import org.hexworks.cobalt.datatypes.Observable

interface InvalidationListener {

    fun invalidated(observable: Observable)
}
