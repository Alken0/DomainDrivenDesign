package com.bartlin.application.reservation

import com.bartlin.domain.reservation.Reservation
import com.bartlin.domain.reservation.ReservationRepository
import com.bartlin.domain.types.Id

class ReservationService(
    private val reservations: ReservationRepository,
) {
    fun create(input: CreateReservationInput) {
        require(input.time.isInFuture()) { "reservation date has to be in the future" }

        val data = Reservation(
            id = Id(0),
            customer = input.customer,
            time = input.time,
            tableId = input.tableId
        )
        reservations.create(data)
    }

    fun deleteById(id: Id) {
        reservations.deleteById(id)
    }

    fun findById(id: Id): ReservationOutput {
        val reservation =
            reservations.findByIdWithTableName(id) ?: throw NoSuchElementException("reservation not found")
        return ReservationOutput(reservation)
    }

    fun findAll(): List<ReservationOutput> {
        return reservations.findAllWithTableName().map { ReservationOutput(it) }
    }
}
