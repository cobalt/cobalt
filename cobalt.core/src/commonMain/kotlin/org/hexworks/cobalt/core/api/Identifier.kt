package org.hexworks.cobalt.core.api

import org.hexworks.cobalt.core.platform.factory.IdentifierFactory

interface Identifier : Comparable<Identifier> {

    companion object {
        
        fun randomIdentifier(): Identifier = IdentifierFactory.randomIdentifier()

        fun fromString(str: String): Identifier = IdentifierFactory.fromString(str)
    }
}
