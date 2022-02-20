package com.bartlin.infrastructure.db.tables

import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object ReservationTable : Table() {
    val id = integer("id").autoIncrement()
    val customer = varchar("customer", 2000)
    val time = long("time")
    val tableId = reference("tableId", TableTable.id)

    override val primaryKey = PrimaryKey(id)
}

fun ResultRow.toReservation() = Reservation(
    id = Id(this[ReservationTable.id]),
    customer = Name(this[ReservationTable.customer]),
    time = Time(this[ReservationTable.time]),
    tableId = Id(this[ReservationTable.tableId])
)
