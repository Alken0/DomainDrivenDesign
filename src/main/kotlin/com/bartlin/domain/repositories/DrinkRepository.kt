package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Drink

interface DrinkRepository {
	fun create(input: Drink)
	fun findById(id: Int): Drink?
	fun update(input: Drink)
	fun findAll(): List<Drink>
}
