package com.bartlin.domain.vo

data class Id(private val value: Int) {
	init {
		require(value >= 0) { "id is negative" }
	}
	
	fun toInt(): Int {
		return value
	}
	
	override fun toString(): String {
		return "$value"
	}
}

fun String.toId(): Id {
	return Id(this.toInt())
}
