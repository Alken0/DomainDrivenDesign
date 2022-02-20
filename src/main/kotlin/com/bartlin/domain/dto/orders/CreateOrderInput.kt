package com.bartlin.domain.dto.orders

import com.bartlin.domain.vo.Id

data class CreateOrderInput(
    val tableId: Id,
    val drinkId: Id,
)
