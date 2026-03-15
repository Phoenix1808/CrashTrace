package com.example.crashtrace_sdk.core

import android.util.Log


object EventTrackers {
    private const val TAG = "CrashTrace"

    fun track(event:String,screen:String, element: String?= null){
        if(element != null){
            Log.d(TAG,"Event: $event | Screen: $screen | Element: $element")
        } else{
            Log.d(TAG,"Event: $event | Screen: $screen")
        }

    }
}