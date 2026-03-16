package com.example.crashtrace_sdk.core

import org.json.JSONArray
import org.json.JSONObject

object CrashReportSerializer {

    fun tojson(report:CrashReport): JSONObject{
        val json = JSONObject()
        json.put("sessionId",report.sessionId)
        json.put("crashMessage",report.crashMessage)
        json.put("screen",report.screen)
        json.put("timestamp",report.timestamp)

        val Arrtimeline = JSONArray()

        report.timeline.forEach {
            Arrtimeline.put(it)
        }
        json.put("timeline",Arrtimeline)

        return json
    }
}