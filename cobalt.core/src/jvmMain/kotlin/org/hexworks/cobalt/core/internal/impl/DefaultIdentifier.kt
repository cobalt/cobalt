package org.hexworks.cobalt.core.internal.impl

import org.hexworks.cobalt.core.api.Identifier
import java.util.*

class DefaultIdentifier(private val backend: UUID = UUID.randomUUID()) : Identifier {

    override fun compareTo(other: Identifier): Int {
        return backend.compareTo(fetchBackend(other))
    }

    override fun toString() = backend.toString()

    override fun equals(other: Any?) = backend == fetchBackend(other)

    override fun hashCode() = backend.hashCode()

    private fun fetchBackend(id: Any?): UUID = (id as? DefaultIdentifier?)?.backend ?: throw IllegalArgumentException(
            "Can't compare an UUID Identifier with an Identifier of a different class.")
}
