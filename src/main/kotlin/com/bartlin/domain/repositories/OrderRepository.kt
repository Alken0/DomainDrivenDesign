package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.entities.Table

interface OrderRepository {
	fun create(table: Table, drink: Drink)
}