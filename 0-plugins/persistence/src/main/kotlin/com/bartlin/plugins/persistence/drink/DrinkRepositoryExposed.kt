package com.bartlin.plugins.persistence.drink

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.types.Id
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

internal class DrinkRepositoryExposed : DrinkRepository {
    override fun create(input: Drink) {
        transaction {
            DrinkTable.insert {
                it[this.name] = input.name.value
                it[this.price] = input.price.cents
                it[this.description] = input.description.value
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
                DrinkTable.select { DrinkTable.id eq id.value }.first().toDrink()
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun update(input: Drink) {
        transaction {
            DrinkTable.update({ DrinkTable.id eq input.id.value }) {
                it[this.name] = input.name.value
                it[this.price] = input.price.cents
                it[this.description] = input.description.value
            }
        }
    }
}
