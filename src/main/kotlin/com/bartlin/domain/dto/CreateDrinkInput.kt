package com.bartlin.domain.dto


data class CreateDrinkInput(
	val name: String,
	val price: Int,
	val description: String = "",
) {
	init {
		require(name.isNotBlank()) { "name is blank" }
		require(price >= 0) { "price is negative" }
	}
}
