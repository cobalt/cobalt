package org.hexworks.cobalt.datatypes.factory

import org.hexworks.cobalt.datatypes.Identifier

expect object IdentifierFactory {

    fun randomIdentifier(): Identifier

    fun fromString(str: String): Identifier
}
