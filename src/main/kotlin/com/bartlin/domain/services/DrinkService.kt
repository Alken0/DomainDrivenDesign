package com.bartlin.domain.services

import com.bartlin.domain.dto.CreateDrinkInput
import com.bartlin.domain.dto.DrinkSummaryOutput
import com.bartlin.domain.dto.UpdateDrinkInput
import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository

class DrinkService(private val drinks: DrinkRepository) {
	fun create(input: CreateDrinkInput) {
		val data = Drink(id = 0, name = input.name, price = input.price, description = input.description)
		drinks.create(data)
	}
	
	fun update(input: UpdateDrinkInput) {
		val original = drinks.findById(input.id) ?: throw Exception("drink not found")
		val updated = original.copy(
			name = input.name ?: original.name,
			price = input.price ?: original.price,
			description = input.description ?: original.description,
		)
		drinks.update(updated)
	}
	
	fun findById(id: Int): DrinkSummaryOutput {
		val drink = drinks.findById(id) ?: throw Exception("drink not found")
		return DrinkSummaryOutput(drink)
	}
	
	fun findAll(): List<DrinkSummaryOutput> {
		return drinks.findAll().map { DrinkSummaryOutput(it) }
	}
}
