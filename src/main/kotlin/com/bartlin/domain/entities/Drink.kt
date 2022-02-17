package com.bartlin.domain.entities

import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price

data class Drink(
	val id: Id,
	val name: Name,
	val price: Price,
	val description: String,
)
