package org.hexworks.cobalt.events;

import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;

public class Main {

    public void foo() {
        KClass<String> kotlinClass = JvmClassMappingKt.getKotlinClass(String.class);
    }
}
