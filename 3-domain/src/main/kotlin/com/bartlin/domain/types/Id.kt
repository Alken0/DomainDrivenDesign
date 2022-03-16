package com.bartlin.domain.types

data class Id(val value: Long) {
    init {
        require(value >= 0) { "id is negative" }
    }

    override fun toString(): String {
        return value.toString()
    }
}
