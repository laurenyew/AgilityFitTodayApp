import org.gradle.api.JavaVersion

object Java {
    val version = JavaVersion.VERSION_17
}

object AndroidSdk {
    const val min = 29
    const val compile = 34
    const val target = compile
}

// Kotlin
const val kotlinVersion = "1.8.22"

object Jetpack {
    const val navigationVersion = "2.7.2"
    const val pagingVersion = "3.2.1"
    const val lifecycleVersion = "2.6.2"
}

object Room {
    const val version = "2.5.2"
}

object Coroutines {
    const val version = "1.6.4"
}

object Networking {
    const val retrofitVersion = "2.9.0"
    const val moshiVersion = "1.9.2"
}

object DependencyInjection {
    const val hiltVersion = "2.44"
    const val daggerVersion = "2.28"
}