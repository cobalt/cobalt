package org.hexworks.kobalt.events

import org.hexworks.cobalt.events.api.*
import org.hexworks.cobalt.events.internal.ApplicationScope
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Suppress("TestFunctionName", "FunctionName")
class EventBusTest {

    private val target = EventBus.create()

    @Test
    fun When_a_subscription_for_a_scope_and_key_is_cancelled_other_subscriptions_for_the_same_combination_shouldnt_be_cancelled() {


        val subscription0 = target.subscribe<TestEvent> {
        }
        val subscription1 = target.subscribe<TestEvent> {
        }

        subscription0.cancel()

        assertFalse(subscription1.cancelled)

    }

    @Test
    fun When_a_subscription_for_a_scope_and_key_is_cancelled_other_subscriptions_for_the_same_combination_should_be_notified_when_an_event_is_published() {

        var sub0Notified = false
        var sub1Notified = false

        val subscription0 = target.subscribe<TestEvent> {
            sub0Notified = true
        }
        val subscription1 = target.subscribe<TestEvent> {
            sub1Notified = true
        }

        subscription0.cancel()
        target.publish(TestEvent)

        assertFalse(sub0Notified)
        assertTrue(sub1Notified)

    }

    @Test
    fun When_subscribed_to_an_event_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        target.subscribe<TestEvent> {
            notified = true
        }

        target.publish(TestEvent)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_and_scope_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        target.subscribe<TestEvent>(TestScope) {
            notified = true
        }

        target.publish(TestEvent, TestScope)

        assertEquals(true, notified, "Subscriber was not notified.")
    }

    @Test
    fun When_subscribed_to_an_event_and_scope_and_the_proper_event_is_broadcasted_but_with_wrong_scope_then_the_subscriber_should_not_be_notified() {

        var notified = false
        target.subscribe<TestEvent> {
            notified = true
        }

        target.publish(TestEvent, TestScope)

        assertEquals(false, notified, "Subscriber should not have been notified.")
    }

    @Test
    fun When_subscribed_to_an_event_with_a_key_and_the_proper_event_is_broadcasted_then_the_subscriber_should_be_notified() {

        var notified = false
        target.subscribe<TestEvent>(key = TestEvent.key) {
            notified = true
        }

        target.publish(TestEvent)

        assertEquals(true, notified, "Subscriber was not notified.")
    }


    @Test
    fun When_EventBus_has_multiple_subscribers_for_the_same_event_all_should_be_notified_when_that_event_is_fired() {

        val notifications = mutableListOf<Int>()

        target.subscribe<TestEvent> {
            notifications += 0
        }
        target.subscribe<TestEvent> {
            notifications += 1
        }

        target.publish(TestEvent)

        assertEquals(listOf(0, 1), notifications, "All subscribers should have been notified.")
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

        target.publish(TestEvent, TestScope)

        assertEquals(mutableListOf<EventScope>(TestScope), notifications, "Only the subscriber with TestScope should have been notified.")
    }

    @Test
    fun When_unsubscribed_from_event_subscriber_should_not_be_present_in_EventBus() {

        target.subscribe<TestEvent> { }.cancel()

        assertEquals(listOf(), target.subscribersFor(ApplicationScope, TestEvent.key).toList(), "Subscribers should be empty.")

    }

    @Test
    fun When_invoking_callback_causes_exception_subscriber_should_be_cancelled_with_exception() {
        val exception = IllegalArgumentException()

        val subscription = target.subscribe<TestEvent> {
            throw exception
        }

        val expectedState = CancelledByException(exception)

        target.publish(TestEvent)

        assertEquals(expected = expectedState, actual = subscription.cancelState,
                message = "Subscriber should have been cancelled with exception")
    }

    @Test
    fun Test() {
        val expectedResult = "foo"
        target.process<TestEvent, String> {
            expectedResult
        }
        var actualResult = ""
        target.send<TestEvent, String>(TestEvent) { (result) ->
            actualResult = result
        }

        assertEquals(expected = expectedResult, actual = actualResult)
    }

    object TestEvent : Event

    object TestScope : EventScope
}
