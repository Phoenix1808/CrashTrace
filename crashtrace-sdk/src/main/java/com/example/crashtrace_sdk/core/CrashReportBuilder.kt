package com.example.crashtrace_sdk.core

object CrashReportBuilder {

    fun report(throwable: Throwable): CrashReport {
        val session = SessionManager.getSessionId()
        val crashMsg = throwable.message
        val timeline = EventBuffer.getEv()
        val screen = timeline.lastOrNull()
        val timestamp = System.currentTimeMillis()

        return CrashReport(
            sessionId = session,
            crashMessage = crashMsg,
            screen = screen,
            timeline = timeline,
            timestamp = timestamp
        )
    }
}