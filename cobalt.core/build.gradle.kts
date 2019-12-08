plugins {
    kotlinMultiplatform
}

group = "org.hexworks.cobalt"

kotlin {

    jvm {
        jvmTarget(JavaVersion.VERSION_1_8)
        withJava()
    }

    js {
    }

    dependencies {

        with(Libs) {
            commonMainApi(kotlinStdLibCommon)
            commonMainApi(kotlinxCoroutines)
            commonMainApi(kotlinReflect)
            jvmMainApi(kotlinStdLibJdk8)
            jsMainApi(kotlinStdLibJs)
        }

        with(TestLibs) {
            commonTestApi(kotlinTestCommon)
            commonTestApi(kotlinTestAnnotationsCommon)
            jvmTestApi(kotlinTestJunit)
            jsTestApi(kotlinTestJs)
        }
    }
}