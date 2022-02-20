package com.bartlin.domain.dto.reservations

import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time

data class ReservationOutput(
    val id: Id,
    val customer: Name,
    val time: Time,
    val tableId: Id
) {
    constructor(reservation: Reservation) : this(
        id = reservation.id,
        customer = reservation.customer,
        time = reservation.time,
        tableId = reservation.tableId
    )
}
