package com.bartlin.domain.services

import com.bartlin.domain.dto.reservations.CreateReservationInput
import com.bartlin.domain.dto.reservations.ReservationOutput
import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.repositories.ReservationRepository
import com.bartlin.domain.vo.Id
import io.ktor.features.*

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
        val reservation = reservations.findById(id) ?: throw NotFoundException("reservation not found")
        return ReservationOutput(reservation)
    }

    fun findAll(): List<ReservationOutput> {
        return reservations.findAll().map { ReservationOutput(it) }
    }
}
