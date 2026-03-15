package com.example.crashtrace_sdk.core

import android.app.Activity
import android.app.Application
import android.os.Bundle

//here we used Application.ActivityLifeCycleCallbacks hook this lets our SDK detect automatically activity created/resumed/pasued
//due to this we can track screen_open & screen_close without writing code inside every activity


class ActivityTracker : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?){}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {
        EventTrackers.track(
            event = "Screen_open",
            screen = activity.javaClass.simpleName
        )
        ClickTrackers.trackClicks(activity)
    }

    override fun onActivityPaused(activity : Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity:Activity) {}
}