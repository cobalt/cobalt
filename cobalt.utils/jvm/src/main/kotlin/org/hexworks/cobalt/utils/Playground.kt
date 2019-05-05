package org.hexworks.cobalt.utils

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object Playground {

    class DynamicInvocationHandler : InvocationHandler {

        @Throws(Throwable::class)
        override operator fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
            println("Invoked method: ${method.name}")
            return 42
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val proxy: MutableMap<String, String> = Proxy.newProxyInstance(
                Playground::class.java.classLoader,
                arrayOf(MutableMap::class.java),
                DynamicInvocationHandler()) as MutableMap<String, String>

        proxy["foo"] = "bar"
    }
}
