package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.events.api.EventScope

/**
 * Default [EventScope] which is used as a default in the [org.hexworks.cobalt.events.api.EventBus]
 * and is a suitable default scope for user-sent [org.hexworks.cobalt.events.api.Event]s.
 */
object ApplicationScope : EventScope
