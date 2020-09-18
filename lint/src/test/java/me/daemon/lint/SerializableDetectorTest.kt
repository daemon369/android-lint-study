package me.daemon.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

internal class SerializableDetectorTest {

    @Test
    fun serializableTest() {
        println("serializableTest start")
        lint()
            .files(
                kotlin(
                    """
                    package me.daemon.lint.demo
                    
                    import java.io.Serializable
                    
                    data class SerializableInner(
                        val string: String
                    )
                    
                    data class SerializableSuper(
                        val inner: SerializableInner
                    ) : Serializable
                    """.trimIndent()
                ).apply {
                    println("content: \n${getContents()}")
                }
            )
            .issues(SerializableDetector.ISSUE)
            .run()
            .expect(
                """
                |src/me/daemon/lint/demo/SerializableInner.kt:10: Error: Inner class must be serializable when outer class is serializable [Serializable Issue]
                |    val inner: SerializableInner
                |               ~~~~~~~~~~~~~~~~~
                |1 errors, 0 warnings
                """.trimMargin()
            )
        println("serializableTest end")
    }

}