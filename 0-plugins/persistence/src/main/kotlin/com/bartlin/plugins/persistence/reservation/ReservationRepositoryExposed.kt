package com.bartlin.plugins.persistence.reservation

import com.bartlin.domain.reservation.Reservation
import com.bartlin.domain.reservation.ReservationRepository
import com.bartlin.domain.reservation.ReservationWithTableName
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time
import com.bartlin.plugins.persistence.table.TableTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

internal class ReservationRepositoryExposed : ReservationRepository {
    override fun create(input: Reservation) {
        transaction {
            ReservationTable.insert {
                it[this.customer] = input.customer.value
                it[this.time] = input.time.milliseconds
                it[this.tableId] = input.tableId.value
            }
        }
    }

    override fun deleteById(id: Id) {
        transaction {
            ReservationTable.deleteWhere { ReservationTable.id eq id.value }
        }
    }

    override fun findByIdWithTableName(id: Id): ReservationWithTableName? {
        return try {
            transaction {
                // JoinType.FULL throws sql-syntax-error
                Join(
                    ReservationTable, TableTable,
                    joinType = JoinType.CROSS
                ).select {
                    (ReservationTable.tableId eq TableTable.id) and (ReservationTable.tableId eq id.value)
                }.first().toReservationWithTableName()
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun findAllWithTableName(): List<ReservationWithTableName> {
        return transaction {
            // JoinType.FULL throws sql-syntax-error
            Join(
                ReservationTable, TableTable,
                joinType = JoinType.CROSS
            )
                .select { ReservationTable.tableId eq TableTable.id }
                .sortedBy { ReservationTable.customer }
                .sortedBy { ReservationTable.tableId }
                .toList()
                .map { it.toReservationWithTableName() }
        }
    }
}

internal fun ResultRow.toReservationWithTableName(): ReservationWithTableName {
    return ReservationWithTableName(
        id = Id(this[ReservationTable.id]),
        customer = Name(this[ReservationTable.customer]),
        time = Time(this[ReservationTable.time]),
        tableName = Name(this[TableTable.name])
    )
}
