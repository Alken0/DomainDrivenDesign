package com.bartlin.plugins.persistence.order

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.order.OrderRepository
import com.bartlin.domain.table.Table
import com.bartlin.plugins.persistence.drink.DrinkTable
import com.bartlin.plugins.persistence.drink.toDrink
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

internal class OrderRepositoryExposed : OrderRepository {
    override fun create(table: Table, drink: Drink) {
        transaction {
            OrderTable.insert {
                it[OrderTable.drink] = drink.id.value
                it[OrderTable.table] = table.id.value
            }
        }
    }

    override fun findDrinksByTable(table: Table): List<Drink> {
        return transaction {
            Join(
                DrinkTable, OrderTable,
                onColumn = DrinkTable.id, otherColumn = OrderTable.drink,
                joinType = JoinType.LEFT
            ).select { OrderTable.table eq table.id.value }
                .map { it.toDrink() }
        }
    }

    override fun deleteByTable(table: Table) {
        transaction {
            OrderTable.deleteWhere { OrderTable.table eq table.id.value }
        }
    }
}
