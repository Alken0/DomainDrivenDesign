package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.toDrink
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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
	
	override fun findAll(): List<Drink> {
		return transaction {
			DrinkTable.selectAll().sortedBy { DrinkTable.name }.toList()
				.map { it.toDrink() }
		}
	}
	
	override fun findById(id: Int): Drink {
		return transaction {
			DrinkTable.select { DrinkTable.id eq id }.first().toDrink()
		}
	}
	
	override fun update(input: Drink) {
		transaction {
			DrinkTable.update ({ DrinkTable.id eq input.id }) {
				it[name] = input.name
				it[price] = input.price
				it[description] = input.description
			}
		}
	}
}
