package com.bartlin.application.drink

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.types.Id

class DrinkService(private val drinks: DrinkRepository) {
    fun create(input: CreateDrinkInput) {
        val data = Drink(id = Id(0), name = input.name, price = input.price, description = input.description)
        drinks.create(data)
    }

    fun update(input: UpdateDrinkInput) {
        val original = drinks.findById(input.id) ?: throw NoSuchElementException("drink not found")
        val updated = original.copy(
            name = input.name ?: original.name,
            price = input.price ?: original.price,
            description = input.description ?: original.description,
        )
        drinks.update(updated)
    }

    fun findById(id: Id): DrinkSummaryOutput {
        val drink = drinks.findById(id) ?: throw NoSuchElementException("drink not found")
        return DrinkSummaryOutput(drink)
    }

    fun findAll(): List<DrinkSummaryOutput> {
        return drinks.findAll().map { DrinkSummaryOutput(it) }
    }
}
