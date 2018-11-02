package examples.web

import org.hexworks.cobalt.databinding.api.expression.concat
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.property.DefaultProperty
import org.hexworks.cobalt.datatypes.extensions.bindTo
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document

fun main(args: Array<String>) {

    val prop: Property<String> = DefaultProperty("")
    val firstName: Property<String> = DefaultProperty("")
    val lastName: Property<String> = DefaultProperty("")
    val fullName = firstName.concat(" ").concat(lastName)

    document.querySelector("#target-field").unsafeCast<HTMLInputElement>().bindTo(prop)
    prop.bindTo(document.querySelector("#source-field").unsafeCast<HTMLInputElement>())

    firstName.bindTo(document.querySelector("#first-name").unsafeCast<HTMLInputElement>())
    lastName.bindTo(document.querySelector("#last-name").unsafeCast<HTMLInputElement>())
    document.querySelector("#full-name").unsafeCast<HTMLInputElement>().let {
        it.disabled = true
        it.bindTo(fullName)
    }

}

