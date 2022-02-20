package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.repositories.ReservationRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time
import com.bartlin.infrastructure.db.tables.ReservationTable
import com.bartlin.infrastructure.db.tables.TableTable
import com.bartlin.infrastructure.db.tables.toReservation
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
        val expected = Reservation(id = Id(1), customer = Name("name"), time = Time.now(), tableId = Id(1))

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
        val reservation = Reservation(id = Id(12), customer = Name("name"), time = Time.now(), tableId = Id(1))

        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }
        }

        repository.create(reservation)

        val elementsWithHighId = transaction {
            ReservationTable.select { ReservationTable.id eq reservation.id.toInt() }.map { it.toReservation() }
        }

        assert(elementsWithHighId.isEmpty())
    }

    @Test
    fun findAllByTableWorks() {
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
                    it[this.tableId] = (i % 2) + 1
                }
            }
        }

        val elements = repository.findAll()

        assertEquals(10, elements.size)
    }

    @Test
    fun findByIdWorks() {
        val expected = Reservation(id = Id(1), customer = Name("name"), time = Time.now(), tableId = Id(1))

        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }

            ReservationTable.insert {
                it[this.customer] = expected.customer.toString()
                it[this.time] = expected.time.toLong()
                it[this.tableId] = expected.tableId.toInt()
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
