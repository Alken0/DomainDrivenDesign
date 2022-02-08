package com.bartlin.domain.dto

data class UpdateDrinkInput (
	val id: Int,
	val name: String? = null,
	val price: Int? = null,
	val description: String? = null
) {
	init {
		if (name != null) {
			require(name.isNotBlank()) { "name is blank" }
		}
		if (price != null) {
			require(price >= 0) { "price is negative" }
		}
	}
}
