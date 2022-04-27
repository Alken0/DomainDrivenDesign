package com.bartlin.application.reservation

import com.bartlin.domain.reservation.ReservationWithTableName
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time

data class ReservationOutput(
    val id: Id,
    val customer: Name,
    val time: Time,
    val tableName: Name
) {
    constructor(reservation: ReservationWithTableName) : this(
        id = reservation.id,
        customer = reservation.customer,
        time = reservation.time,
        tableName = reservation.tableName
    )
}
