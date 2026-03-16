package com.example.crashtrace_sdk.core

import java.sql.Timestamp

data class CrashReport(
    val sessionId: String?,
    val crashMessage: String?,
    val screen: String?,
    val timeline: List<String>,
    val timestamp: Long
)