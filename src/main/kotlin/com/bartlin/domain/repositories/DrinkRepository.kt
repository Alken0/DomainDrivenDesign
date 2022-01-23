package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Drink

interface DrinkRepository {
	fun create(input: Drink)
	fun findAll(): List<Drink>
}
