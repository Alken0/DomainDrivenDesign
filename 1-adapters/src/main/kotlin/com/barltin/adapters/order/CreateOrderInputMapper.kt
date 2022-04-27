package com.barltin.adapters.order

import com.bartlin.application.order.CreateOrderInput
import com.bartlin.domain.types.Id

data class CreateOrderInputMapper(
    val tableId: Long,
    val drinkId: Long,
) {
    constructor(tableId: String?, drinkId: String?) : this(
        tableId = tableId?.toLong() ?: throw IllegalArgumentException("tableId is required"),
        drinkId = drinkId?.toLong() ?: throw IllegalArgumentException("drinkId is required")
    )

    fun toCreateOrderInput(): CreateOrderInput {
        return CreateOrderInput(
            tableId = Id(tableId),
            drinkId = Id(drinkId)
        )
    }
}
