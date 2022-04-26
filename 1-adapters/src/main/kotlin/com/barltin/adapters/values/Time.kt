package com.barltin.adapters.values

import com.bartlin.domain.types.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun String.parseSimpleDateFormat(): Time {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    format.timeZone = TimeZone.getTimeZone("GMT+2")
    val date = format.parse(this)
    return Time(date.time)
}

fun Time.toSimpleDateFormat(): String {
    return Timestamp(this.milliseconds).toString()
}
