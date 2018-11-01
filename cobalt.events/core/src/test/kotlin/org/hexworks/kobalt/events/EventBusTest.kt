package org.hexworks.kobalt.events

import org.hexworks.cobalt.events.CancelledByException
import org.hexworks.cobalt.events.Event
import org.hexworks.cobalt.events.EventBus
import org.hexworks.cobalt.events.EventScope
import org.hexworks.cobalt.events.impl.ApplicationScope
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("TestFunctionName", "FunctionName")
class EventBusTest {

    @Test
    fun When_subscribed_to_an_event_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        EventBus.subscribe<TestEvent> {
            notified = true
        }

        EventBus.broadcast(TestEvent)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_and_scope_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        EventBus.subscribe<TestEvent>(TestScope) {
            notified = true
        }

        EventBus.broadcast(TestEvent, TestScope)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_and_scope_and_the_proper_event_is_broadcasted_but_with_wrong_scope_then_the_subscriber_should_not_be_notified() {

        var notified = false
        EventBus.subscribe<TestEvent> {
            notified = true
        }

        EventBus.broadcast(TestEvent, TestScope)

        assertEquals(false, notified, "Subscriber should not have been notified.")
    }

    @Test
    fun When_subscribed_to_an_event_with_a_prototype_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        EventBus.subscribe<TestEventPrototype>(TestScope, TestEventPrototype) {
            notified = true
        }

        EventBus.broadcast(TestEventPrototype, TestScope)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_with_a_key_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        EventBus.subscribe<TestEventPrototype>(key = TestEventPrototype.key) {
            notified = true
        }

        EventBus.broadcast(TestEventPrototype)

        assertEquals(true, notified, "Subscriber was not notified.")
    }


    @Test
    fun When_EventBus_has_multiple_subscribers_for_the_same_event_all_should_be_notified_when_that_event_is_fired() {

        var notifications = 0

        EventBus.subscribe<TestEvent> {
            notifications++
        }
        EventBus.subscribe<TestEvent> {
            notifications++
        }

        EventBus.broadcast(TestEvent)

        assertEquals(2, notifications, "All subscribers should have been notified.")
    }

    @Test
    fun When_EventBus_has_multiple_subscribers_for_the_same_event_but_different_scopes_only_one_should_be_notified_when_that_event_is_fired() {

        val notifications = mutableListOf<EventScope>()

        EventBus.subscribe<TestEvent>(TestScope) {
            notifications.add(TestScope)
        }
        EventBus.subscribe<TestEvent>(ApplicationScope) {
            notifications.add(ApplicationScope)
        }

        EventBus.broadcast(TestEvent, TestScope)

        assertEquals(mutableListOf<EventScope>(TestScope), notifications, "Only the subscriber with TestScope should have been notified.")
    }

    @Test
    fun When_unsubscribed_from_event_subscriber_should_not_be_present_in_EventBus() {

        EventBus.subscribe<TestEvent> { }.cancel()

        assertEquals(listOf(), EventBus.subscribers, "Subscribers should be empty.")

    }

    @Test
    fun When_invoking_callback_causes_exception_subscriber_should_be_cancelled_with_exception() {
        val exception = IllegalArgumentException()

        val subscription = EventBus.subscribe<TestEvent> {
            throw exception
        }

        val expectedState = CancelledByException(exception)

        EventBus.broadcast(TestEvent)

        assertEquals(expected = expectedState, actual = subscription.cancelState,
                message = "Subscriber should have been cancelled with exception")
    }

    object TestEvent : Event

    object TestEventPrototype : Event {
        override val key: String = "fubar"
    }

    object TestScope : EventScope

}
