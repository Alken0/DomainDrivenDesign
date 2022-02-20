package com.bartlin.domain.dto.drinks

import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price

data class UpdateDrinkInput(
    val id: Id,
    val name: Name? = null,
    val price: Price? = null,
    val description: String? = null
)
