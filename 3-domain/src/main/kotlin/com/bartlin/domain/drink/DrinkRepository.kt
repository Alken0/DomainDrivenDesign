package com.bartlin.domain.drink

import com.bartlin.domain.types.Id

interface DrinkRepository {
    fun create(input: Drink)
    fun findById(id: Id): Drink?
    fun update(input: Drink)
    fun findAll(): List<Drink>
}
