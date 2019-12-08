import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object Projects {

    inline val DependencyHandlerScope.cobaltCore get() = project(":cobalt.core")
    inline val DependencyHandlerScope.cobaltDatabinding get() = project(":cobalt.databinding")
    inline val DependencyHandlerScope.cobaltDatatypes get() = project(":cobalt.datatypes")
    inline val DependencyHandlerScope.cobaltEvents get() = project(":cobalt.events")
    inline val DependencyHandlerScope.cobaltHtml get() = project(":cobalt.html")
    inline val DependencyHandlerScope.cobaltLogging get() = project(":cobalt.logging")
    inline val DependencyHandlerScope.cobaltNetworking get() = project(":cobalt.networking")

}