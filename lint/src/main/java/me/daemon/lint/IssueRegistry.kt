package me.daemon.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class IssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            SerializableDetector.ISSUE
        )

    override val api: Int
        get() = CURRENT_API

}