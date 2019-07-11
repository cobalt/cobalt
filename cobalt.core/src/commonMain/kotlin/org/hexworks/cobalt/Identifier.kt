package org.hexworks.cobalt.datatypes

import org.hexworks.cobalt.factory.IdentifierFactory

interface Identifier : Comparable<Identifier> {

    companion object {

        fun randomIdentifier(): Identifier = IdentifierFactory.randomIdentifier()
    }
}
