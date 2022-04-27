package com.bartlin.domain.price

import com.bartlin.domain.types.Price

private const val HAPPY_HOUR_DISCOUNT = 0.4

class HappyHourPriceStrategy : PriceStrategy {

    override fun calculatePrice(prices: List<Price>): Price {
        val total = prices.sumOf { it.cents }
        val discounted = (total * HAPPY_HOUR_DISCOUNT).toLong()
        return Price(discounted)
    }

    override fun discount(): Double {
        return HAPPY_HOUR_DISCOUNT
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
