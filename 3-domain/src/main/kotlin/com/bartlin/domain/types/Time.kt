package com.bartlin.domain.types

data class Time(val milliseconds: Long) {
    init {
        require(milliseconds >= 0) { "time is negative" }
    }

    fun isInFuture(): Boolean {
        return milliseconds > System.currentTimeMillis()
    }

    override fun toString(): String {
        return milliseconds.toString()
    }
}
