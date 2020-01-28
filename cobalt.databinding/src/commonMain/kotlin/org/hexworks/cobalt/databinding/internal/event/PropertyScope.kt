package org.hexworks.cobalt.databinding.internal.event

import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.events.api.EventScope

/**
 * [EventScope] which can be used within property and binding objects.
 */
data class PropertyScope(val id: Identifier) : EventScope