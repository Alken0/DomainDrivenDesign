package com.bartlin.domain.vo

data class Price(
	private val cents: Int
) {
	init {
		require(cents >= 0) { "price is negative" }
	}
	
	fun toCents(): Int {
		return cents
	}
	
	fun toEuro(): String {
		return "${cents / 100f} €"
	}
}

fun String.toPrice(): Price {
	return Price(this.toInt())
}
