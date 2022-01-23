package com.bartlin.domain.dto

import com.bartlin.domain.entities.Drink

data class DrinkSummaryOutput(
	val id: Int,
	val name: String,
	val price: Int,
	val description: String = "",
) {
	constructor(drink: Drink) : this(
		id = drink.id,
		name = drink.name,
		price = drink.price,
		description = drink.description
	)
}
