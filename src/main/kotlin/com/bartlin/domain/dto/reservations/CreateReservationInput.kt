package com.bartlin.domain.dto.reservations

import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time

data class CreateReservationInput(
    val customer: Name,
    val time: Time,
    val tableId: Id
)
