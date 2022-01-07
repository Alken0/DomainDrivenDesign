package com.bartlin.domain.services

import com.bartlin.domain.dto.CreateDrinkInput
import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository

class DrinkService(private val drinks: DrinkRepository) {
	fun create(input: CreateDrinkInput) {
		val data = Drink(id = 0, name = input.name, price = input.price, description = input.description)
		drinks.create(data)
	}
}
