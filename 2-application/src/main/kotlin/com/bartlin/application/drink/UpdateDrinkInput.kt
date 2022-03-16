package com.bartlin.application.drink

import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class UpdateDrinkInput(
    val id: Id,
    val name: Name? = null,
    val price: Price? = null,
    val description: Description? = null
)
