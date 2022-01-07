package com.bartlin.domain.entities


data class Drink(
	val id: Int,
	val name: String,
	val price: Int,
	val description: String = "",
) {
	init {
		require(id >= 0) { "id is negativ" }
		require(name.isNotBlank()) { "name is blank" }
		require(price >= 0) { "price is negative" }
	}
}
