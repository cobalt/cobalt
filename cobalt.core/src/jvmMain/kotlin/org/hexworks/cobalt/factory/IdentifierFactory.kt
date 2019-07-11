package org.hexworks.cobalt.factory

import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.cobalt.impl.DefaultIdentifier
import java.util.*

actual object IdentifierFactory {

    actual fun randomIdentifier(): Identifier {
        return DefaultIdentifier()
    }

    actual fun fromString(str: String): Identifier {
        return DefaultIdentifier(UUID.fromString(str))
    }

}
