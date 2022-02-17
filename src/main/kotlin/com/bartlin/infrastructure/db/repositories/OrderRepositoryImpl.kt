package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.OrderRepository
import com.bartlin.infrastructure.db.tables.OrderTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class OrderRepositoryImpl : OrderRepository {
	override fun create(table: Table, drink: Drink) {
		transaction {
			OrderTable.insert {
				it[this.drink] = drink.id
				it[this.table] = table.id
			}
		}
	}
}
