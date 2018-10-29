package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.Observable

interface InvalidationListener {

    fun invalidated(observable: Observable)
}
