package com.bartlin.domain.dto.drinks

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price

data class DrinkSummaryOutput(
    val id: Id,
    val name: Name,
    val price: Price,
    val description: String = "",
) {
    constructor(drink: Drink) : this(
        id = drink.id,
        name = drink.name,
        price = drink.price,
        description = drink.description
    )
}
