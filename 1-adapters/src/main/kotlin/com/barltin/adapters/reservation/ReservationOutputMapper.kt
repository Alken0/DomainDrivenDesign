package com.barltin.adapters.reservation

import com.barltin.adapters.values.toSimpleDateFormat
import com.bartlin.application.reservation.ReservationOutput

data class ReservationOutputMapper(
    val id: String,
    val customer: String,
    val time: String,
    val tableName: String
)

fun ReservationOutput.toMapper(): ReservationOutputMapper {
    return ReservationOutputMapper(
        id = this.id.value.toString(),
        customer = this.customer.value,
        time = this.time.toSimpleDateFormat(),
        tableName = this.tableName.value
    )
}
