package com.bartlin.domain.dto

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.vo.Price

class BillOutput(private val drinks: List<Drink>) {
	fun total(): Price {
		val total = drinks.sumOf { it.price.toCents() }
		return Price(total)
	}

	fun orders(): List<BillDrinkSummaryOutput> {
		return drinks.map { BillDrinkSummaryOutput(it) }
	}
}
