package com.bartlin.application.reservation

import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time

data class CreateReservationInput(
    val customer: Name,
    val time: Time,
    val tableId: Id
)
