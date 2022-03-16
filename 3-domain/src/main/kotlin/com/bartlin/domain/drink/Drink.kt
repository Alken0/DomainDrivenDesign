package com.bartlin.domain.drink

import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class Drink(
    val id: Id,
    val name: Name,
    val price: Price,
    val description: Description,
)
