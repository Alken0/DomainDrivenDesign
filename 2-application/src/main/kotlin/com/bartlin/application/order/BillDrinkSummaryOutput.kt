package com.bartlin.application.order

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class BillDrinkSummaryOutput(
    val name: Name,
    val price: Price
) {
    constructor(input: Drink) : this(input.name, input.price)
}
