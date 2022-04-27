package com.bartlin.application.drink

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class DrinkSummaryOutput(
    val id: Id,
    val name: Name,
    val price: Price,
    val description: Description,
) {
    constructor(drink: Drink) : this(
        id = drink.id,
        name = drink.name,
        price = drink.price,
        description = drink.description
    )
}
