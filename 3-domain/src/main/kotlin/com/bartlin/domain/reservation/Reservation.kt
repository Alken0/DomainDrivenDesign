package com.bartlin.domain.reservation

import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time

data class Reservation(
    val id: Id,
    val customer: Name,
    val time: Time,
    val tableId: Id
)
