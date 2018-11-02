package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.cobalt.datatypes.factory.IdentifierFactory
import org.hexworks.cobalt.events.api.EventScope

data class ChangeEventScope(val id: Identifier = IdentifierFactory.randomIdentifier()) : EventScope
