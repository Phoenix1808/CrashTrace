package com.example.crashtrace_sdk.core

import android.util.Log


object EventTrackers {
    private const val TAG = "CrashTrace"

    fun track(event:String,screen:String, element: String?= null){
        val log = if(element != null){
            "Event: $event | Screen: $screen | Element: $element"
        } else{
            "Event: $event | Screen: $screen"
        }
        Log.d(TAG,log)

        EventBuffer.addEv(log) //connected eventBuffer with eventTracker
    }
}