package com.example.crashtrace_sdk.core

import android.util.Log
import kotlin.concurrent.thread

//Thread.UnCaughtExceptionHandler lets SDK detect crashes before android kills the app.

object CrashHandler: Thread.UncaughtExceptionHandler {

    private const val TAG = "CrashTrace"

    private var defHandler: Thread.UncaughtExceptionHandler? = null
    fun init(){
        Log.d("CrashTrace","CrashHandler Registered")
        defHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {

        val report = CrashReportBuilder.report(throwable)

        val jsonRep = CrashReportSerializer.tojson(report)
        Log.e(TAG,"---Crash JSON---")
        Log.e(TAG,jsonRep.toString(2))

        Log.e(TAG, "------ Crash Report ------")

        Log.e(TAG, "Session: ${report.sessionId}")
        Log.e(TAG, "Crash Message: ${report.crashMessage}")
        Log.e(TAG, "Timestamp: ${report.timestamp}")

        Log.e(TAG, "--- User Events Timeline ---")

        report.timeline.forEach {
            Log.e(TAG, it)
        }

        defHandler?.uncaughtException(thread, throwable)
    }}
