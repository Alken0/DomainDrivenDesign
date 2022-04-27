package com.bartlin.application.order

import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.order.OrderRepository
import com.bartlin.domain.table.TableRepository
import com.bartlin.domain.types.Id

class OrderService(
    private val drinks: DrinkRepository,
    private val tables: TableRepository,
    private val orders: OrderRepository
) {
    fun create(input: CreateOrderInput) {
        val table = tables.findById(input.tableId) ?: throw NoSuchElementException("table not found")
        val drink = drinks.findById(input.drinkId) ?: throw NoSuchElementException("drink not found")

        orders.create(table, drink)
    }

    fun getBillForTable(id: Id): BillOutput {
        val table = tables.findById(id) ?: throw NoSuchElementException("table not found")
        val drinks = orders.findDrinksByTable(table)
        return BillOutput(drinks)
    }

    fun clearTable(id: Id) {
        val table = tables.findById(id) ?: throw NoSuchElementException("table not found")
        orders.deleteByTable(table)
    }
}
