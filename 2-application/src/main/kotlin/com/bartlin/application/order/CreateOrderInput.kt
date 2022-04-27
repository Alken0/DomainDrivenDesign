package com.bartlin.application.order

import com.bartlin.domain.types.Id

data class CreateOrderInput(
    val tableId: Id,
    val drinkId: Id,
)
