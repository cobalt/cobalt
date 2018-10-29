package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.cobalt.events.EventScope

data class ChangeEventScope(val id: Identifier) : EventScope
