package com.bartlin.plugins.persistence

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price
import com.bartlin.plugins.persistence.drink.DrinkTable
import com.bartlin.plugins.persistence.drink.toDrink
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Test if tests run independent of one another.
 * Tests have to be run in order and in one run!
 */
internal class TestDatabaseTest : DatabaseTest(arrayOf(DrinkTable)) {
    @Test
    fun createElements() {
        transaction {
            DrinkTable.insert {
                it[this.name] = "name"
                it[this.price] = 123
                it[this.description] = "description"
            }
        }
        val result = transaction {
            DrinkTable.selectAll().sortedBy { DrinkTable.name }.toList()
                .map { it.toDrink() }
        }
        assertEquals(result, listOf(Drink(Id(1), Name("name"), Price(123), Description("description"))))

    }

    @Test
    fun expectDatabaseToBeEmpty() {
        val result = transaction {
            DrinkTable.selectAll().sortedBy { DrinkTable.name }.toList()
                .map { it.toDrink() }
        }
        assertEquals(result, listOf())
    }
}
