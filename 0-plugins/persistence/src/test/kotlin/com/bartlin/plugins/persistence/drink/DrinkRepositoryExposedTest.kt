package com.bartlin.plugins.persistence.drink

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price
import com.bartlin.plugins.persistence.DatabaseTest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DrinkRepositoryExposedTest : DatabaseTest(arrayOf(DrinkTable)) {

    private val repository: DrinkRepository = DrinkRepositoryExposed()

    @Test
    fun createWorks() {
        val expected = Drink(Id(1), Name("name"), Price(123), Description("description"))

        repository.create(expected)

        val elements = transaction {
            DrinkTable.selectAll().map { it.toDrink() }
        }

        assertEquals(listOf(expected), elements)
    }

    @Test
    fun createIgnoresId() {
        val drink = Drink(Id(12421), Name("name"), Price(123), Description("description"))

        repository.create(drink)

        val elementsWithHighId = transaction {
            DrinkTable.select { DrinkTable.id eq drink.id.value }.map { it.toDrink() }
        }

        assert(elementsWithHighId.isEmpty())
    }

    @Test
    fun findAllWorks() {
        transaction {
            for (i in 0..9) {
                DrinkTable.insert {
                    it[this.name] = "name"
                    it[this.price] = 12
                    it[this.description] = "description"
                }
            }
        }

        val elements = repository.findAll()

        assertEquals(10, elements.size)
    }

    @Test
    fun findByIdWorks() {
        val expected = Drink(Id(1), Name("name"), Price(123), Description("description"))

        transaction {
            DrinkTable.insert {
                it[this.name] = expected.name.value
                it[this.price] = expected.price.cents
                it[this.description] = expected.description.value
            }
        }

        val result = repository.findById(Id(1))

        assertEquals(expected, result)
    }

    @Test
    fun findByIdWorksReturnsNullIfNothingFound() {
        val result = repository.findById(Id(1))

        assertEquals(null, result)
    }

    @Test
    fun updateWorks() {
        val origin = Drink(Id(1), Name("name"), Price(123), Description(""))
        val updated = Drink(Id(1), Name("updated"), Price(1), Description(""))

        transaction {
            DrinkTable.insert {
                it[this.name] = origin.name.value
                it[this.price] = origin.price.cents
                it[this.description] = origin.description.value
            }
        }

        repository.update(updated)

        val result = transaction {
            DrinkTable.select { DrinkTable.id eq origin.id.value }.first().toDrink()
        }

        assertEquals(result, updated)
    }
}
