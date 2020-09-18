@file:Suppress("UnstableApiUsage")

package me.daemon.lint

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiClassType
import org.jetbrains.uast.UClass
import java.util.*

class SerializableDetector : Detector(), SourceCodeScanner {

    companion object {

        const val CLASS_NAME = "java.io.Serializable"

        private const val ISSUE_NAME = "Serializable class field issue"
        private const val ISSUE_MESSAGE_BRIEF = "Serializable class fields must be serializable too"
        private const val ISSUE_MESSAGE = "Serializable class fields must be serializable too"

        private val IMPL =
            Implementation(
                SerializableDetector::class.java,
                EnumSet.of(Scope.ALL_JAVA_FILES)
            )

        val ISSUE = Issue.create(
            id = ISSUE_NAME,
            briefDescription = ISSUE_MESSAGE_BRIEF,
            explanation = ISSUE_MESSAGE,
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.ERROR,
            implementation = IMPL
        )

    }

    override fun applicableSuperClasses(): List<String>? = listOf(CLASS_NAME)

    override fun visitClass(context: JavaContext, declaration: UClass) {
        declaration.fields.forEach {
            val psiClass = (it.type as? PsiClassType)?.resolve() ?: return@forEach
            if (!context.evaluator.implementsInterface(psiClass, CLASS_NAME, true)) {
                it.typeReference?.let { reference ->
                    context.report(
                        issue = ISSUE,
                        location = context.getLocation(reference),
                        message = ISSUE_MESSAGE
                    )
                }
            }
        }
    }

}