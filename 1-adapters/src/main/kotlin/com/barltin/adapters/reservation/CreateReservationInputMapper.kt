package com.barltin.adapters.reservation

import com.barltin.adapters.values.parseSimpleDateFormat
import com.bartlin.application.reservation.CreateReservationInput
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name

data class CreateReservationInputMapper(
    val customer: String,
    val time: String,
    val tableId: Long
) {
    constructor(customer: String?, time: String?, tableId: String?) : this(
        customer = customer ?: throw IllegalArgumentException("customer name is required"),
        time = time ?: throw IllegalArgumentException("time is required"),
        tableId = tableId?.toLong() ?: throw IllegalArgumentException("tableId is required")
    )

    fun toCreateReservationInput(): CreateReservationInput {
        return CreateReservationInput(
            customer = Name(customer),
            time = time.parseSimpleDateFormat(),
            tableId = Id(tableId)
        )
    }
}
