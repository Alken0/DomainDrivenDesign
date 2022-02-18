package com.bartlin.domain.vo

data class Name(private val value: String) {
    init {
        require(value.isNotBlank()) { "name is blank" }
    }

    override fun toString(): String {
        return value
    }
}

fun String.toName(): Name {
    return Name(this)
}
