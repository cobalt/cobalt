@file:Suppress("UNUSED_PARAMETER")

package org.hexworks.cobalt.databinding.internal.collections

import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.collections.ListProperty
import org.hexworks.cobalt.databinding.api.collections.ObservableList
import org.hexworks.cobalt.databinding.api.converter.IdentityConverter
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.internal.extensions.runWithDisposeOnFailure
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo

@Suppress("UNCHECKED_CAST")
class DefaultListProperty<T : Any>(
        private val backingList: MutableList<T>)
    : ListProperty<T>, MutableList<T> by backingList {

    override fun onChange(fn: (ObservableValueChanged<List<T>>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribeTo<ObservableValueChanged<ListProperty<T>>>(scope) {
            fn(it)
        }
    }

    override var value: ListProperty<T>
        get() = this
        set(value) {
            throw UnsupportedOperationException(
                    "Can't set the value of a ListProperty directly. Try using clear + addAll")
        }

    private val id = Identifier.randomIdentifier()
    private val scope = PropertyScope(id)
    private val listIdentityConverter = IdentityConverter<T>()
    private val bindings: MutableList<ListBinding<out Any, out Any>> = mutableListOf()

    override fun add(element: T): Boolean {
        bindings.forEach {
            val target = it.target as ListProperty<Any>
            val converter = it.converter as IsomorphicConverter<T, Any>
            target.add(converter.convert(element))
        }
        return backingList.add(element)
    }

    override fun add(index: Int, element: T) {
        bindings.forEach {
            val target = it.target as ListProperty<Any>
            val converter = it.converter as IsomorphicConverter<T, Any>
            target.add(index, converter.convert(element))
        }
        return backingList.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<T>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): MutableIterator<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(): MutableListIterator<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(element: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAt(index: Int): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(index: Int, element: T): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Property functions

    override fun bind(other: ListProperty<T>): Binding<ListProperty<T>> {
        return bind(other, listIdentityConverter)
    }

    override fun updateFrom(observable: ObservableList<T>): Binding<ObservableList<T>> {
        return updateFrom(observable, listIdentityConverter::convert)
    }

    override fun <U : Any> bind(other: ListProperty<U>, converter: IsomorphicConverter<T, U>): Binding<ListProperty<T>> {
        return ListBinding(
                source = this,
                target = other,
                converter = converter).also {
            bindings.add(it)
        }
    }

    override fun <U : Any> updateFrom(observable: ObservableList<U>, converter: (U) -> T): Binding<ObservableList<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private inner class ListBinding<S : Any, T : Any>(
            val source: ListProperty<S>,
            val target: ListProperty<T>,
            val converter: IsomorphicConverter<S, T>) : Binding<ListProperty<S>> {

        override val value = source

        override var disposeState: DisposeState = NotDisposed
            private set

        private val id = Identifier.randomIdentifier()
        private val scope = PropertyScope(id)

        private val listeners: MutableList<Subscription> = mutableListOf(
                source.onChange { changeEvent ->
                    runWithDisposeOnFailure {
                        Cobalt.eventbus.publish(
                                event = changeEvent,
                                eventScope = scope)
                    }
                })

        override fun dispose(disposeState: DisposeState) {
            Cobalt.eventbus.cancelScope(scope)
            this.disposeState = disposeState
            listeners.clearSubscriptions()
            bindings.remove(this)
        }

        override fun onChange(fn: (ObservableValueChanged<ListProperty<S>>) -> Unit): Subscription {
            return Cobalt.eventbus.subscribeTo<ObservableValueChanged<ListProperty<S>>>(scope) {
                fn(it)
            }
        }
    }
}
