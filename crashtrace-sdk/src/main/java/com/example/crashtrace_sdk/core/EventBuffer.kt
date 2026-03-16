package com.example.crashtrace_sdk.core

object EventBuffer {
    private const val Max_Event = 20
    private val events = mutableListOf<String>()

    fun addEv(event:String){
        if(events.size >= Max_Event){
            events.removeAt(0) // removes the oldest one from memory
        }
        events.add(event)
    }

    fun getEv(): List<String>{
        return events.toList() //returns the events in list
    }

    fun clr(){
        events.clear() //clrs the events
    }


}