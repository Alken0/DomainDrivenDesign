package com.bartlin.application.drink

import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class CreateDrinkInput(
    val name: Name,
    val price: Price,
    val description: Description,
)
