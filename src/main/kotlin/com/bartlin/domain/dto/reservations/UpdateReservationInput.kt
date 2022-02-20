package com.bartlin.domain.dto.reservations

import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time

data class UpdateReservationInput(
    val id: Id,
    val customer: Name? = null,
    val time: Time? = null,
    val tableId: Id? = null
)
