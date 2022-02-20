package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.vo.Id

interface ReservationRepository {
    fun create(input: Reservation)
    fun deleteById(id: Id)
    fun findById(id: Id): Reservation?
    fun findAll(): List<Reservation>
}
