package com.barltin.adapters.order

import com.bartlin.application.order.BillDrinkSummaryOutput

data class BillDrinkSummaryOutputMapper(
    val name: String,
    val priceInEuro: Double
)

fun BillDrinkSummaryOutput.toMapper(): BillDrinkSummaryOutputMapper {
    return BillDrinkSummaryOutputMapper(
        name = this.name.value,
        priceInEuro = this.price.cents / 100.0
    )
}
