package com.barltin.adapters.drink

import com.bartlin.application.drink.DrinkSummaryOutput

data class DrinkSummaryOutputMapper(
    val id: String,
    val name: String,
    val priceInCents: Long,
    val priceInEuros: Double,
    val description: String,
)

fun DrinkSummaryOutput.toMapper(): DrinkSummaryOutputMapper {
    return DrinkSummaryOutputMapper(
        id = "${this.id.value}",
        name = this.name.value,
        priceInCents = this.price.cents,
        priceInEuros = this.price.cents / 100.0,
        description = this.description.value
    )
}
