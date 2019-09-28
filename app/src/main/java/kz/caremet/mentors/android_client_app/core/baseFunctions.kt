package kz.caremet.mentors.android_client_app.core

import java.text.SimpleDateFormat
import java.util.*

fun generateUUID(): String{
    return UUID.randomUUID().toString()
}

fun getCurrentDateISOString(): String{
    val tz = TimeZone.getTimeZone("UTC")
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") // Quoted "Z" to indicate UTC, no timezone offset
    df.timeZone = tz
    return df.format(Date())
}