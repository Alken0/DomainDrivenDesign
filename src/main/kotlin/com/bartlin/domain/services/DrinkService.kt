package com.bartlin.domain.services

import com.bartlin.domain.dto.drinks.CreateDrinkInput
import com.bartlin.domain.dto.drinks.DrinkSummaryOutput
import com.bartlin.domain.dto.drinks.UpdateDrinkInput
import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.vo.Id
import io.ktor.features.*

class DrinkService(private val drinks: DrinkRepository) {
    fun create(input: CreateDrinkInput) {
        val data = Drink(id = Id(0), name = input.name, price = input.price, description = input.description)
        drinks.create(data)
    }

    fun update(input: UpdateDrinkInput) {
        val original = drinks.findById(input.id) ?: throw NotFoundException("drink not found")
        val updated = original.copy(
            name = input.name ?: original.name,
            price = input.price ?: original.price,
            description = input.description ?: original.description,
        )
        drinks.update(updated)
    }

    fun findById(id: Id): DrinkSummaryOutput {
        val drink = drinks.findById(id) ?: throw NotFoundException("drink not found")
        return DrinkSummaryOutput(drink)
    }

    fun findAll(): List<DrinkSummaryOutput> {
        return drinks.findAll().map { DrinkSummaryOutput(it) }
    }
}
