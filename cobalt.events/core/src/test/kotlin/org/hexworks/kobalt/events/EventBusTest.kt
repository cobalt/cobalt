package org.hexworks.kobalt.events

import org.hexworks.cobalt.events.Event
import org.hexworks.cobalt.events.EventBus
import org.hexworks.cobalt.events.EventScope
import org.hexworks.cobalt.events.impl.ApplicationScope
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("FunctionName")
class EventBusTest {

    @Test
    fun `When subscribed to an event and the proper event is broadcasted then the subscriber should be notified`() {

        var notified = false
        EventBus.subscribe<TestEvent> {
            notified = true
        }

        EventBus.broadcast(TestEvent)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun `When subscribed to an event and scope and the proper event is broadcasted then the subscriber should be notified`() {

        var notified = false
        EventBus.subscribe<TestEvent>(TestScope) {
            notified = true
        }

        EventBus.broadcast(TestEvent, TestScope)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun `When subscribed to an event and scope and the proper event is broadcasted but with wrong scope then the subscriber should not be notified`() {

        var notified = false
        EventBus.subscribe<TestEvent> {
            notified = true
        }

        EventBus.broadcast(TestEvent, TestScope)

        assertEquals(false, notified, "Subscriber should not have been notified.")
    }

    @Test
    fun `When subscribed to an event with a prototype and the proper event is broadcasted then the subscriber should be notified`() {

        var notified = false
        EventBus.subscribe<TestEventPrototype>(TestScope, TestEventPrototype) {
            notified = true
        }

        EventBus.broadcast(TestEventPrototype, TestScope)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun `When subscribed to an event with a key and the proper event is broadcasted then the subscriber should be notified`() {

        var notified = false
        EventBus.subscribe<TestEventPrototype>(key = TestEventPrototype.key) {
            notified = true
        }

        EventBus.broadcast(TestEventPrototype)

        assertEquals(true, notified, "Subscriber was not notified.")
    }


    @Test
    fun `When unsubscribed from event, subscriber should not be present in EventBus`() {

        EventBus.subscribe<TestEvent> {  }.cancel()

        assertEquals(listOf(), EventBus.subscribers, "Subscribers should be empty.")

    }

    @Test
    fun `When EventBus has multiple subscribers for the same event, all should be notified when that event is fired`() {

        var notifications = 0

        EventBus.subscribe<TestEvent>{
            notifications++
        }
        EventBus.subscribe<TestEvent>{
            notifications++
        }

        EventBus.broadcast(TestEvent)

        assertEquals(2, notifications, "All subscribers should have been notified.")
    }

    @Test
    fun `When EventBus has multiple subscribers for the same event but different scopes, only one should be notified when that event is fired`() {

        val notifications = mutableListOf<EventScope>()

        EventBus.subscribe<TestEvent>(TestScope){
            notifications.add(TestScope)
        }
        EventBus.subscribe<TestEvent>(ApplicationScope){
            notifications.add(ApplicationScope)
        }

        EventBus.broadcast(TestEvent, TestScope)

        assertEquals(mutableListOf<EventScope>(TestScope), notifications, "Only the subscriber with TestScope should have been notified.")
    }



    object TestEvent : Event

    object TestEventPrototype : Event {
        override val key: String = "fubar"
    }

    object TestScope : EventScope

}
