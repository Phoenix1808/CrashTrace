package com.example.crashtrace_sdk.core

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Button

object ClickTrackers {

    //sdk calls whenever activity starts
    fun trackClicks(activity: Activity){

        //activity.window => every activity has window object
        //decorView => it is  the topmost view of screen
        //rootView => give entire UI hierarchy
        val root = activity.window.decorView.rootView
        traverseViews(root,activity)
    }

    private fun traverseViews(view:View,activity: Activity){
        if(view is Button){
//            val orgListener = view.hasOnClickListeners()
            if(!view.hasOnClickListeners()){
            view.setOnClickListener {
                //exception handling
                val btnName = try {
                    activity.resources.getResourceEntryName(view.id)
                } catch (e: Exception) {
                    "unknown_btn"
                }
                //analytics tracker
                EventTrackers.track(
                    event = "btn_click",
                    screen = activity.javaClass.simpleName, //gets activity class name
                    element = btnName
                )
            }
            }
        }
        if(view is ViewGroup){
            for(i in 0 until view.childCount){
                traverseViews(view.getChildAt(i),activity)
            }
        }
    }
}

//gets root view
//traverses entire UI tree
//detect btns
// attach click listener
//send analytics event