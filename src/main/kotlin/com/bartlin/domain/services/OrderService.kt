package com.bartlin.domain.services

import com.bartlin.domain.dto.BillOutput
import com.bartlin.domain.dto.CreateOrderInput
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.repositories.OrderRepository
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id
import io.ktor.features.*

class OrderService(
    private val drinks: DrinkRepository,
    private val tables: TableRepository,
    private val orders: OrderRepository
) {
    fun create(input: CreateOrderInput) {
        val table = tables.findById(input.tableId) ?: throw NotFoundException("table not found")
        val drink = drinks.findById(input.drinkId) ?: throw NotFoundException("drink not found")

        orders.create(table, drink)
    }

    fun getBillForTable(id: Id): BillOutput {
        val table = tables.findById(id) ?: throw NotFoundException("table not found")
        val drinks = orders.findDrinksByTable(table)
        return BillOutput(drinks)
    }

    fun clearTable(id: Id) {
        val table = tables.findById(id) ?: throw NotFoundException("table not found")
        orders.deleteByTable(table)
    }
}
