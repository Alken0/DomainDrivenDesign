package com.bartlin.domain.dto

import com.bartlin.domain.vo.Id

data class CreateOrderInput(
	val tableId: Id,
	val drinkId: Id,
)
