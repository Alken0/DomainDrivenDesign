package com.bartlin.domain.dto

import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price

data class CreateDrinkInput(
	val name: Name,
	val price: Price,
	val description: String,
)
