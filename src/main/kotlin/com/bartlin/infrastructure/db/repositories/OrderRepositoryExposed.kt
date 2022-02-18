package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.OrderRepository
import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.OrderTable
import com.bartlin.infrastructure.db.tables.toDrink
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class OrderRepositoryExposed : OrderRepository {
    override fun create(table: Table, drink: Drink) {
        transaction {
            OrderTable.insert {
                it[this.drink] = drink.id.toInt()
                it[this.table] = table.id.toInt()
            }
        }
    }

    override fun findDrinksByTable(table: Table): List<Drink> {
        return transaction {
            DrinkTable.join(OrderTable, JoinType.LEFT, onColumn = DrinkTable.id, otherColumn = OrderTable.drink)
                .select { OrderTable.table eq table.id.toInt() }.map { it.toDrink() }
        }
    }

    override fun deleteByTable(table: Table) {
        transaction {
            OrderTable.deleteWhere { OrderTable.table eq table.id.toInt() }
        }
    }
}
