package org.hexworks.cobalt.databinding.event

import org.hexworks.cobalt.databinding.Observable

interface InvalidationListener {

    fun invalidated(observable: Observable)
}
