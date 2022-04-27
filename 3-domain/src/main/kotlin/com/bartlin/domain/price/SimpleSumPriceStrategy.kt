package com.bartlin.domain.price

import com.bartlin.domain.types.Price

class SimpleSumPriceStrategy : PriceStrategy {
    override fun calculatePrice(prices: List<Price>): Price {
        val total = prices.sumOf { it.cents }
        return Price(total)
    }

    override fun discount(): Double {
        return 0.0
    }

    // comparison needed for tests
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
