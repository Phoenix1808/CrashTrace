package com.example.crashtrace_sdk.core

import android.util.Log
import java.util.UUID

object SessionManager {

    private const val TAG = "CrashTrace"
    private var sessionId: String? = null

    fun startSession(){
        sessionId = UUID.randomUUID().toString()
        Log.d(TAG,"Session Started: $sessionId")
    }

    fun getSessionId():String?{
        return sessionId
    }
}