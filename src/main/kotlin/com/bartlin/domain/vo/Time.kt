package com.bartlin.domain.vo

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class Time(private val value: Timestamp) {
    constructor(value: Long) : this(Timestamp(value))

    companion object {
        fun now(): Time {
            return Time(Timestamp(System.currentTimeMillis()))
        }

        fun parse(value: String): Time {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
            format.timeZone = TimeZone.getTimeZone("GMT+2")
            val date = format.parse(value)
            return Time(date.time)
        }
    }

    fun isInFuture(): Boolean {
        return value.time > System.currentTimeMillis()
    }

    fun toLong(): Long {
        return value.time
    }

    override fun toString(): String {
        return value.toString()
    }
}
