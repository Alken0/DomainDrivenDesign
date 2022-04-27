package com.bartlin.application.order

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.price.PriceStrategy
import com.bartlin.domain.price.SimpleSumPriceStrategy
import com.bartlin.domain.types.Price

data class BillOutput(
    private val drinks: List<Drink>,
    private val priceStrategy: PriceStrategy = SimpleSumPriceStrategy()
) {
    fun total(): Price {
        return priceStrategy.calculatePrice(drinks.map { it.price })
    }

    fun discount(): Double {
        return priceStrategy.discount()
    }

    fun orders(): List<BillDrinkSummaryOutput> {
        return drinks.map { BillDrinkSummaryOutput(it) }
    }
}
