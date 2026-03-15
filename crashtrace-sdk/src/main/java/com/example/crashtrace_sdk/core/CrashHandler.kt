package com.example.crashtrace_sdk.core

import android.util.Log
import kotlin.concurrent.thread

object CrashHandler {

    private const val TAG = "CrashTrace"

    fun init(){
        val defHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread , throwable ->
            Log.e(TAG,"App Crashed: ${throwable.message}")

            val sessionId = SessionManager.getSessionId()
            Log.e(TAG,"Session ID: $sessionId")

            defHandler?.uncaughtException(thread,throwable)
        }
    }
}