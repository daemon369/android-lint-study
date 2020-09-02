package me.daemon.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class IssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = TODO("Not yet implemented")

}