package com.bartlin.domain.dto

data class CreateOrderInput(
	val tableId: Int,
	val drinkId: Int,
) {
	init {
		require(tableId >= 0) { "tableId is negative" }
		require(drinkId >= 0) { "drinkId is negative" }
	}
}
