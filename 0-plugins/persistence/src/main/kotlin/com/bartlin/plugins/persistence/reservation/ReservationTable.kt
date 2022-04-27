package com.bartlin.plugins.persistence.reservation

import com.bartlin.domain.reservation.Reservation
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time
import com.bartlin.plugins.persistence.table.TableTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

internal object ReservationTable : Table() {
    val id = long("id").autoIncrement()
    val customer = varchar("customer", 2000)
    val time = long("time")
    val tableId = reference("tableId", TableTable.id)

    override val primaryKey = PrimaryKey(id)
}

internal fun ResultRow.toReservation() = Reservation(
    id = Id(this[ReservationTable.id]),
    customer = Name(this[ReservationTable.customer]),
    time = Time(this[ReservationTable.time]),
    tableId = Id(this[ReservationTable.tableId])
)
