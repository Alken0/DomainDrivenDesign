package com.bartlin.plugins.persistence.order

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.order.OrderRepository
import com.bartlin.domain.table.Table
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price
import com.bartlin.plugins.persistence.DatabaseTest
import com.bartlin.plugins.persistence.drink.DrinkTable
import com.bartlin.plugins.persistence.table.TableTable
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
        val drink = Drink(Id(1), Name("name"), Price(123), Description(""))
        val table = Table(Id(1), Name("Table 1"))

        transaction {
            DrinkTable.insert {
                it[this.name] = drink.name.value
                it[this.price] = drink.price.cents
                it[this.description] = drink.description.value
            }
            TableTable.insert {
                it[this.name] = "Table 1"
            }
        }

        repository.create(table, drink)

        val amountCreated = transaction {
            OrderTable.selectAll().count()
        }

        assertEquals(1, amountCreated)
    }

    @Test
    fun findDrinksByTableWorks() {
        val drink = Drink(Id(1), Name("name"), Price(123), Description(""))
        val table = Table(Id(1), Name("Table 1"))

        transaction {
            DrinkTable.insert {
                it[this.name] = drink.name.value
                it[this.price] = drink.price.cents
                it[this.description] = drink.description.value
            }
            TableTable.insert {
                it[this.name] = "Table 1"
            }
            OrderTable.insert {
                it[OrderTable.table] = table.id.value
                it[OrderTable.drink] = drink.id.value
            }
        }

        val result = repository.findDrinksByTable(table)

        assertEquals(listOf(drink), result)
    }

    @Test
    fun deleteByTableWorks() {
        val drink = Drink(Id(1), Name("name"), Price(123), Description(""))
        val table = Table(Id(1), Name("Table 1"))

        transaction {
            DrinkTable.insert {
                it[this.name] = drink.name.value
                it[this.price] = drink.price.cents
                it[this.description] = drink.description.value
            }
            TableTable.insert {
                it[this.name] = "Table 1"
            }
            OrderTable.insert {
                it[OrderTable.table] = table.id.value
                it[OrderTable.drink] = drink.id.value
            }
        }

        repository.deleteByTable(table)

        val amountOfDrinksForTable = transaction {
            OrderTable.select { OrderTable.drink eq drink.id.value }.count()
        }

        assertEquals(0, amountOfDrinksForTable)
    }
}
