package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.infrastructure.db.tables.DrinkTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class DrinkRepositoryImpl : DrinkRepository {
	override fun create(input: Drink) {
		transaction {
			DrinkTable.insert {
				it[this.name] = input.name
				it[this.price] = input.price
				it[this.description] = input.description
			}
		}
	}
}
