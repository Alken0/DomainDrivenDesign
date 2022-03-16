package com.bartlin.domain.reservation

import com.bartlin.domain.types.Id

interface ReservationRepository {
    fun create(input: Reservation)
    fun deleteById(id: Id)
    fun findByIdWithTableName(id: Id): ReservationWithTableName?
    fun findAllWithTableName(): List<ReservationWithTableName>
}
