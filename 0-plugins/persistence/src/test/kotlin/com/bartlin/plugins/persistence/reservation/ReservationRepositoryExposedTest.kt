package com.bartlin.plugins.persistence.reservation

import com.bartlin.domain.reservation.Reservation
import com.bartlin.domain.reservation.ReservationRepository
import com.bartlin.domain.reservation.ReservationWithTableName
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time
import com.bartlin.plugins.persistence.DatabaseTest
import com.bartlin.plugins.persistence.table.TableTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ReservationRepositoryExposedTest : DatabaseTest(arrayOf(ReservationTable, TableTable)) {

    private val repository: ReservationRepository = ReservationRepositoryExposed()

    @Test
    fun createWorks() {
        val expected =
            Reservation(id = Id(1), customer = Name("name"), time = Time(System.currentTimeMillis()), tableId = Id(1))

        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }
        }

        repository.create(expected)

        val elements = transaction {
            ReservationTable.selectAll().map { it.toReservation() }
        }

        assertEquals(listOf(expected), elements)
    }

    @Test
    fun createIgnoresId() {
        val reservation =
            Reservation(id = Id(12), customer = Name("name"), time = Time(System.currentTimeMillis()), tableId = Id(1))

        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }
        }

        repository.create(reservation)

        val elementsWithHighId = transaction {
            ReservationTable.select { ReservationTable.id eq reservation.id.value }.map { it.toReservation() }
        }

        assert(elementsWithHighId.isEmpty())
    }

    @Test
    fun findAllWithTableNameWorks() {
        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }
            TableTable.insert {
                it[this.name] = "Table 2"
            }

            for (i in 0..9) {
                ReservationTable.insert {
                    it[this.customer] = "customer"
                    it[this.time] = 124135421341123
                    it[this.tableId] = (i % 2) + 1L
                }
            }
        }

        val elements = repository.findAllWithTableName()

        assertEquals(10, elements.size)
    }

    @Test
    fun findByIdWorks() {
        val expected = ReservationWithTableName(
            id = Id(1),
            customer = Name("name"),
            time = Time(System.currentTimeMillis()),
            tableName = Name("Table 1")
        )

        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }

            ReservationTable.insert {
                it[this.customer] = expected.customer.value
                it[this.time] = expected.time.milliseconds
                it[this.tableId] = 1
            }
        }

        val result = repository.findByIdWithTableName(Id(1))

        assertEquals(expected, result)
    }

    @Test
    fun findByIdWithTableNameReturnsNullIfNothingFound() {
        val result = repository.findByIdWithTableName(Id(1))

        assertEquals(null, result)
    }

    @Test
    fun deleteByIdWorks() {
        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }

            for (i in 0..9) {
                ReservationTable.insert {
                    it[this.customer] = "customer"
                    it[this.time] = 124135421341123
                    it[this.tableId] = 1
                }
            }
        }

        repository.deleteById(Id(1))

        val count = transaction {
            ReservationTable.selectAll().count()
        }
        assertEquals(9, count)
    }
}
