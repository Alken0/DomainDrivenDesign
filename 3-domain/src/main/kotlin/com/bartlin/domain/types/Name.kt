package com.bartlin.domain.types

data class Name(val value: String) {
    init {
        require(value.isNotBlank()) { "name is blank" }
    }

    override fun toString(): String {
        return value
    }
}
