package com.bartlin.domain.services

import com.bartlin.domain.dto.CreateOrderInput
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.repositories.OrderRepository
import com.bartlin.domain.repositories.TableRepository

class OrderService(
	private val drinks: DrinkRepository,
	private val tables: TableRepository,
	private val orders: OrderRepository
) {
	fun create(input: CreateOrderInput) {
		val table = tables.findById(input.tableId) ?: throw Exception("table not found")
		val drink = drinks.findById(input.drinkId) ?: throw Exception("drink not found")

		orders.create(table, drink)
	}
}
