package com.bartlin.application.order

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.types.Price

data class BillOutput(private val drinks: List<Drink>) {
    fun total(): Price {
        val total = drinks.sumOf { it.price.cents }
        return Price(total)
    }

    fun orders(): List<BillDrinkSummaryOutput> {
        return drinks.map { BillDrinkSummaryOutput(it) }
    }
}
