package org.hexworks.cobalt.core.extensions

import org.hexworks.cobalt.core.api.Identifier

fun Identifier.abbreviate() = toString().substring(0, 4)