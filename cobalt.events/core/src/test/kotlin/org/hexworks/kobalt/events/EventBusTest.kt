package org.hexworks.kobalt.events

import org.hexworks.cobalt.events.CancelledByException
import org.hexworks.cobalt.events.Event
import org.hexworks.cobalt.events.EventBus
import org.hexworks.cobalt.events.EventScope
import org.hexworks.cobalt.events.impl.ApplicationScope
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("TestFunctionName", "FunctionName")
class EventBusTest {

    private val target = EventBus()

    @Test
    fun When_subscribed_to_an_event_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        target.subscribe<TestEvent> {
            notified = true
        }

        target.broadcast(TestEvent)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_and_scope_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        target.subscribe<TestEvent>(TestScope) {
            notified = true
        }

        target.broadcast(TestEvent, TestScope)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_and_scope_and_the_proper_event_is_broadcasted_but_with_wrong_scope_then_the_subscriber_should_not_be_notified() {

        var notified = false
        target.subscribe<TestEvent> {
            notified = true
        }

        target.broadcast(TestEvent, TestScope)

        assertEquals(false, notified, "Subscriber should not have been notified.")
    }

    @Test
    fun When_subscribed_to_an_event_with_a_key_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        target.subscribe<TestEvent>(key = TestEvent.key) {
            notified = true
        }

        target.broadcast(TestEvent)

        assertEquals(true, notified, "Subscriber was not notified.")
    }


    @Test
    fun When_EventBus_has_multiple_subscribers_for_the_same_event_all_should_be_notified_when_that_event_is_fired() {

        var notifications = 0

        target.subscribe<TestEvent> {
            notifications++
        }
        target.subscribe<TestEvent> {
            notifications++
        }

        target.broadcast(TestEvent)

        assertEquals(2, notifications, "All subscribers should have been notified.")
    }

    @Test
    fun When_EventBus_has_multiple_subscribers_for_the_same_event_but_different_scopes_only_one_should_be_notified_when_that_event_is_fired() {

        val notifications = mutableListOf<EventScope>()

        target.subscribe<TestEvent>(TestScope) {
            notifications.add(TestScope)
        }
        target.subscribe<TestEvent>(ApplicationScope) {
            notifications.add(ApplicationScope)
        }

        target.broadcast(TestEvent, TestScope)

        assertEquals(mutableListOf<EventScope>(TestScope), notifications, "Only the subscriber with TestScope should have been notified.")
    }

    @Test
    fun When_unsubscribed_from_event_subscriber_should_not_be_present_in_EventBus() {

        target.subscribe<TestEvent> { }.cancel()

        assertEquals(listOf(), target.subscribers, "Subscribers should be empty.")

    }

    @Test
    fun When_invoking_callback_causes_exception_subscriber_should_be_cancelled_with_exception() {
        val exception = IllegalArgumentException()

        val subscription = target.subscribe<TestEvent> {
            throw exception
        }

        val expectedState = CancelledByException(exception)

        target.broadcast(TestEvent)

        assertEquals(expected = expectedState, actual = subscription.cancelState,
                message = "Subscriber should have been cancelled with exception")
    }

    object TestEvent : Event

    object TestScope : EventScope

}
