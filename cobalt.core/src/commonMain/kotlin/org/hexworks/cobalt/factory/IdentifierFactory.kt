package org.hexworks.cobalt.factory

import org.hexworks.cobalt.Identifier

expect object IdentifierFactory {

    fun randomIdentifier(): Identifier

    fun fromString(str: String): Identifier
}
