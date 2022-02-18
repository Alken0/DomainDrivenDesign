package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.OrderRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price
import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.OrderTable
import com.bartlin.infrastructure.db.tables.TableTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class OrderRepositoryExposedTest : DatabaseTest(arrayOf(DrinkTable, TableTable, OrderTable)) {
    private val repository: OrderRepository = OrderRepositoryExposed()

    @Test
    fun createWorks() {
        val drink = Drink(Id(1), Name("name"), Price(123), "")
        val table = Table(Id(1))

        transaction {
            DrinkTable.insert {
                it[this.name] = drink.name.toString()
                it[this.price] = drink.price.toCents()
                it[this.description] = drink.description
            }
            TableTable.insert { }
        }

        repository.create(table, drink)

        val amountCreated = transaction {
            OrderTable.selectAll().count()
        }

        assertEquals(1, amountCreated)
    }

    @Test
    fun findDrinksByTableWorks() {
        val drink = Drink(Id(1), Name("name"), Price(123), "")
        val table = Table(Id(1))

        transaction {
            DrinkTable.insert {
                it[this.name] = drink.name.toString()
                it[this.price] = drink.price.toCents()
                it[this.description] = drink.description
            }
            TableTable.insert { }
            OrderTable.insert {
                it[this.table] = table.id.toInt()
                it[this.drink] = drink.id.toInt()
            }
        }

        val result = repository.findDrinksByTable(table)

        assertEquals(listOf(drink), result)
    }

    @Test
    fun deleteByTableWorks() {
        val drink = Drink(Id(1), Name("name"), Price(123), "")
        val table = Table(Id(1))

        transaction {
            DrinkTable.insert {
                it[this.name] = drink.name.toString()
                it[this.price] = drink.price.toCents()
                it[this.description] = drink.description
            }
            TableTable.insert { }
            OrderTable.insert {
                it[this.table] = table.id.toInt()
                it[this.drink] = drink.id.toInt()
            }
        }

        repository.deleteByTable(table)

        val amountOfDrinksForTable = transaction {
            OrderTable.select { OrderTable.drink eq drink.id.toInt() }.count()
        }

        assertEquals(0, amountOfDrinksForTable)
    }
}
