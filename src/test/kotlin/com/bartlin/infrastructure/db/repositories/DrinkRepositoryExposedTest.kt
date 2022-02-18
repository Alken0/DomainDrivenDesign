package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price
import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.toDrink
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
		val expected = Drink(Id(1), Name("name"), Price(123), "description")

		repository.create(expected)

		val elements = transaction {
			DrinkTable.selectAll().map { it.toDrink() }
		}

		assertEquals(listOf(expected), elements)
	}

	@Test
	fun createIgnoresId() {
		val drink = Drink(Id(12421), Name("name"), Price(123), "description")

		repository.create(drink)

		val elementsWithHighId = transaction {
			DrinkTable.select { DrinkTable.id eq drink.id.toInt() }.map { it.toDrink() }
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
		val expected = Drink(Id(1), Name("name"), Price(123), "description")

		transaction {
			DrinkTable.insert {
				it[this.name] = expected.name.toString()
				it[this.price] = expected.price.toCents()
				it[this.description] = expected.description
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
		val origin = Drink(Id(1), Name("name"), Price(123), "")
		val updated = Drink(Id(1), Name("updated"), Price(1), "")

		transaction {
			DrinkTable.insert {
				it[this.name] = origin.name.toString()
				it[this.price] = origin.price.toCents()
				it[this.description] = origin.description
			}
		}

		repository.update(updated)

		val result = transaction {
			DrinkTable.select { DrinkTable.id eq origin.id.toInt() }.first().toDrink()
		}

		assertEquals(result, updated)
	}
}
