package com.bartlin.domain.entities

import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time

data class Reservation(
    val id: Id,
    val customer: Name,
    val time: Time,
    val tableId: Id
)
