package com.example.crashtrace_sdk.core

import android.app.Application
import android.content.Context
import android.util.Log

object CrashTrace {

    private const val TAG = "CrashTrace"

    fun init(context: Context){
        Log.d(TAG,"CrashTrace Initialized")
        SessionManager.startSession()
        CrashHandler.init()

        val application = context.applicationContext as Application
        application.registerActivityLifecycleCallbacks(ActivityTracker())

    }
}