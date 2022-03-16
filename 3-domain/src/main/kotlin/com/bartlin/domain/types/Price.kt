package com.bartlin.domain.types

data class Price(
    val cents: Long
) {
    init {
        require(cents >= 0) { "price is negative" }
    }

    override fun toString(): String {
        return cents.toString()
    }
}
