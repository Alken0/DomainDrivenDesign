package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.vo.Id

interface DrinkRepository {
	fun create(input: Drink)
	fun findById(id: Id): Drink?
	fun update(input: Drink)
	fun findAll(): List<Drink>
}
