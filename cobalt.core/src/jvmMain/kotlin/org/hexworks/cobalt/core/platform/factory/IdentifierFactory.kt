package org.hexworks.cobalt.core.platform.factory

import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.core.internal.impl.DefaultIdentifier
import java.util.*

actual object IdentifierFactory {

    actual fun randomIdentifier(): Identifier {
        return DefaultIdentifier()
    }

    actual fun fromString(str: String): Identifier {
        return DefaultIdentifier(UUID.fromString(str))
    }

}