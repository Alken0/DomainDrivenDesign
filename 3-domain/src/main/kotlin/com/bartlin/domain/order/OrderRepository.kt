package com.bartlin.domain.order

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.table.Table

interface OrderRepository {
    fun create(table: Table, drink: Drink)
    fun findDrinksByTable(table: Table): List<Drink>
    fun deleteByTable(table: Table)
}
