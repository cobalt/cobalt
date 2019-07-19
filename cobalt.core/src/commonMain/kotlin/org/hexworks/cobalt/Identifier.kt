package org.hexworks.cobalt

import org.hexworks.cobalt.factory.IdentifierFactory

interface Identifier : Comparable<Identifier> {

    companion object {

        fun randomIdentifier(): Identifier = IdentifierFactory.randomIdentifier()
    }
}
