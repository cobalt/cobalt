package org.hexworks.cobalt.persistent

import org.hexworks.cobalt.datatypes.Maybe
import kotlin.math.abs
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Suppress("TestFunctionName")
class HashArrayMappedTrieTest {

    @Test
    fun Given_a_HAMT_putting_into_it_should_return_a_new_map_containing_the_value() {

        val expected = "foo" to 1

        val result = empty<String, Int>().put("foo", 1)

        assertEquals(expected, result.iterator().next())
    }

    @Test
    fun Given_a_HAMT_putting_into_it_should_not_modify_the_original() {

        val target = empty<String, Int>()
        target.put("foo", 1)

        assertTrue {
            target.isEmpty
        }
    }

    @Test
    fun Given_two_HAMTs_which_have_the_same_content_they_should_be_equal() {

        val first = empty<String, Int>().put("foo", 3)
        val second = empty<String, Int>().put("foo", 3)

        assertEquals(first.toString(), second.toString())
    }

    // NOTICE: these tests are ported from VAVR verbatim
    @Test
    fun testLeafSingleton() {
        var hamt = empty<WeaklyHashedInteger, Int>()
        hamt = hamt.put(WeaklyHashedInteger(1), 1)
        assertEquals(hamt[WeaklyHashedInteger(1)], Maybe.of(1))
        assertEquals(hamt[WeaklyHashedInteger(11)], Maybe.empty())
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(1), 2), 1)
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(11), 2), 2)
        assertEquals(hamt[WeaklyHashedInteger(2)], Maybe.empty())
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(2), 2), 2)
    }

    @Test
    fun testLeafList() {
        var hamt = empty<WeaklyHashedInteger, Int>()
        hamt = hamt.put(WeaklyHashedInteger(1), 1).put(WeaklyHashedInteger(31), 31)
        assertEquals(hamt[WeaklyHashedInteger(1)], Maybe.of(1))
        assertEquals(hamt[WeaklyHashedInteger(11)], Maybe.empty())
        assertEquals(hamt[WeaklyHashedInteger(31)], Maybe.of(31))
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(1), 2), 1)
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(11), 2), 2)
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(31), 2), 31)
        assertEquals(hamt[WeaklyHashedInteger(2)], Maybe.empty())
        assertEquals(hamt.getOrElse(WeaklyHashedInteger(2), 2), 2)
    }

    @Test
    fun testGetExistingKey() {
        var hamt = empty<Int?, Int>()
        hamt = hamt.put(1, 2).put(4, 5).put(null, 7)
        assertTrue {
            hamt.containsKey(1)
        }
        assertEquals(hamt.get(1), Maybe.of(2))
        assertEquals(hamt.getOrElse(1, 42), 2)
        assertTrue {
            hamt.containsKey(4)
        }
        assertEquals(hamt[4], Maybe.of(5))
        assertTrue {
            hamt.containsKey(null)
        }
        assertEquals(hamt[null], Maybe.of(7))
    }

    @Test
    fun testGetUnknownKey() {
        var hamt = empty<Int?, Int>()
        assertEquals(hamt[2], Maybe.empty())
        assertEquals(hamt.getOrElse(2, 42), 42)
        hamt = hamt.put(1, 2).put(4, 5)
        assertFalse {
            hamt.containsKey(2)
        }
        assertEquals(hamt[2], Maybe.empty())
        assertEquals(hamt.getOrElse(2, 42), 42)
        assertFalse {
            hamt.containsKey(null)
        }
        assertEquals(hamt[null], Maybe.empty())
    }

    @Test
    fun testRemoveFromEmpty() {
        var hamt = empty<Int, Int>()
        hamt = hamt.remove(1)
        assertEquals(hamt.size, 0)
    }

    @Test
    fun testRemoveUnknownKey() {
        var hamt = empty<Int, Int>()
        hamt = hamt.put(1, 2).remove(3)
        assertEquals(hamt.size, 1)
        hamt = hamt.remove(1)
        assertEquals(hamt.size, 0)
    }

    @Test
    fun testDeepestTree() {
        val ints = 0.until(Int.SIZE_BITS).map { 1 shl it }.sorted()
        var hamt = empty<Int, Int>()
        hamt = ints.fold(hamt, { h, i -> h.put(i, i) })
        assertEquals(hamt.keysIterator().asSequence().toList().sorted(), ints)
    }

    @Test
    fun testBigData() {
        testBigData(5000) { t -> t }
    }

    @Test
    fun testBigDataWeakHashCode() {
        testBigData(5000) { t -> WeaklyHashedInteger(t.first) to t.second }
    }

    private fun <K : Comparable<K>, V> testBigData(count: Int, mapper: (Pair<Int, Int>) -> Pair<K, V>) {
        val cmp = Comparator<K, V>()
        val rnd = rnd(count, mapper)
        for ((key, value) in rnd) {
            cmp[key] = value
        }
        cmp.test()
        for (key in rnd.keys.toSet().sorted()) {
            rnd.remove(key)
            cmp.remove(key)
        }
        cmp.test()
    }

    @Test
    fun shouldLookupNullInZeroKey() {
        var trie = empty<Int?, Int>()
        // should contain all node types
        for (i in 0..4999) {
            trie = trie.put(i, i)
        }
        trie = trie.put(null, 2)
        assertEquals(trie[0].get(), 0)     // key.hashCode = 0
        assertEquals(trie[null].get(), 2)  // key.hashCode = 0
    }

    @Test
    fun shouldMakeString() {
        assertEquals("", empty<Any, Any>().toString())
        assertEquals("(1, 2)", empty<Any, Any>().put(1, 2).toString())
    }

    private fun <K, V> empty(): HashArrayMappedTrie<K, V> {
        return HashArrayMappedTrie.empty()
    }

    /**
     * This class is used to enforce hash collisions for the test by having a weak
     * [hashCode] method.
     */
    private class WeaklyHashedInteger(internal val value: Int) : Comparable<WeaklyHashedInteger> {

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other == null || this::class != other::class) {
                return false
            }
            val that = other as WeaklyHashedInteger?
            return value == that!!.value
        }

        override fun hashCode(): Int {
            return abs(value) % 10
        }

        override fun compareTo(other: WeaklyHashedInteger): Int {
            return value.compareTo(other.value)
        }
    }

    private inner class Comparator<K, V> {
        private val classic = mutableMapOf<K, V>()
        private var hamt = empty<K, V?>()

        internal fun test() {
            assertEquals(hamt.size, classic.size)
            hamt.iterator().forEach { e ->
                assertEquals(classic[e.first], e.second)
            }
            classic.forEach { (k, v) ->
                assertEquals(hamt[k].get(), v)
                assertEquals(hamt.getOrElse(k, null), v)
            }
        }

        internal operator fun set(key: K, value: V) {
            classic[key] = value
            hamt = hamt.put(key, value)
        }

        internal fun remove(key: K) {
            classic.remove(key)
            hamt = hamt.remove(key)
        }
    }

    private fun <K, V> rnd(count: Int, mapper: (Pair<Int, Int>) -> Pair<K, V>): MutableMap<K, V> {
        val r = Random(2134234)
        val mp = mutableMapOf<K, V>()
        for (i in 0 until count) {
            val entry = mapper(r.nextInt() to r.nextInt())
            mp[entry.first] = entry.second
        }
        return mp
    }
}
