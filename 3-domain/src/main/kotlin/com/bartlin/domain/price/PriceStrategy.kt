package com.bartlin.domain.price

import com.bartlin.domain.types.Price

interface PriceStrategy {
    fun calculatePrice(prices: List<Price>): Price
    fun discount(): Double
}
