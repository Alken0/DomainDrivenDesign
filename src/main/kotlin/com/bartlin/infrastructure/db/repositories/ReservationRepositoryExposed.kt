package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.repositories.ReservationRepository
import com.bartlin.domain.vo.Id
import com.bartlin.infrastructure.db.tables.ReservationTable
import com.bartlin.infrastructure.db.tables.toReservation
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ReservationRepositoryExposed : ReservationRepository {
    override fun create(input: Reservation) {
        transaction {
            ReservationTable.insert {
                it[this.customer] = input.customer.toString()
                it[this.time] = input.time.toLong()
                it[this.tableId] = input.tableId.toInt()
            }
        }
    }

    override fun deleteById(id: Id) {
        transaction {
            ReservationTable.deleteWhere { ReservationTable.id eq id.toInt() }
        }
    }

    override fun findById(id: Id): Reservation? {
        return try {
            transaction {
                ReservationTable.select { ReservationTable.id eq id.toInt() }.first().toReservation()
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun findAll(): List<Reservation> {
        return transaction {
            ReservationTable
                .selectAll()
                .sortedBy { ReservationTable.customer }
                .sortedBy { ReservationTable.tableId }
                .toList()
                .map { it.toReservation() }
        }
    }
}
