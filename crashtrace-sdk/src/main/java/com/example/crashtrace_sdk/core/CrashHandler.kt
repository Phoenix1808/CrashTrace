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

    override fun uncaughtException(thread:Thread,throwable:Throwable){
        Log.e(TAG,"App Crash Detected !")
        Log.e(TAG,"Crash Msg: ${throwable.message}")
        Log.e(TAG,"Session: ${SessionManager.getSessionId()}")

        try {
            Thread.sleep(200)   // allow logs to flush
        } catch (e: Exception) {}

        defHandler?.uncaughtException(thread,throwable)
    }
}
