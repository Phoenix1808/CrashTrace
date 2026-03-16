package com.example.crashtrace_sdk.core

import android.view.View

object ReflectionUtils {

    fun getExistingClickListener(view: View): View.OnClickListener? {
        return try {

            val listenerInfoField = View::class.java.getDeclaredField("mListenerInfo")
            listenerInfoField.isAccessible = true

            val listenerInfo = listenerInfoField.get(view) ?: return null

            val listenerClass = Class.forName("android.view.View\$ListenerInfo")
            val onClickField = listenerClass.getDeclaredField("mOnClickListener")
            onClickField.isAccessible = true

            onClickField.get(listenerInfo) as? View.OnClickListener

        } catch (e: Exception) {
            null
        }
    }

    //before it was like if button already has listener then SDK skips tracking of it
    //but now SDK wraps the listeners in itself
    //now user clicks then logs event then org app listener executes and app continues

}