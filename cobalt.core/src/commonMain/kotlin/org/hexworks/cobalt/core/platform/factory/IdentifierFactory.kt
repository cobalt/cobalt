package org.hexworks.cobalt.core.platform.factory

import org.hexworks.cobalt.core.api.Identifier

expect object IdentifierFactory {

    fun randomIdentifier(): Identifier

    fun fromString(str: String): Identifier
}
