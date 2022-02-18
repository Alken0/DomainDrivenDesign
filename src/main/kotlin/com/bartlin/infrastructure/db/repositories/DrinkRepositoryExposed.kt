package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.vo.Id
import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.toDrink
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class DrinkRepositoryExposed : DrinkRepository {
    override fun create(input: Drink) {
        transaction {
            DrinkTable.insert {
                it[this.name] = input.name.toString()
                it[this.price] = input.price.toCents()
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

    override fun findById(id: Id): Drink? {
        return try {
            transaction {
                DrinkTable.select { DrinkTable.id eq id.toInt() }.first().toDrink()
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun update(input: Drink) {
        transaction {
            DrinkTable.update({ DrinkTable.id eq input.id.toInt() }) {
                it[name] = input.name.toString()
                it[price] = input.price.toCents()
                it[description] = input.description
            }
        }
    }
}
