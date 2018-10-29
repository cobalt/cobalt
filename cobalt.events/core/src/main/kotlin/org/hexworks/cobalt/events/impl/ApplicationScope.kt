package org.hexworks.cobalt.events.impl

import org.hexworks.cobalt.events.EventScope

/**
 * Default [EventScope] which is used as a default in the
 * [org.hexworks.cobalt.events.EventBus] and is a suitable
 * default scope for user-broadcasted [org.hexworks.cobalt.events.Event]s.
 */
object ApplicationScope : EventScope
