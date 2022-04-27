package com.barltin.adapters.order

import com.bartlin.application.order.BillOutput
import kotlin.math.roundToInt

data class BillOutputMapper(
    private val original: BillOutput
) {
    fun totalInEuros(): Double {
        return original.total().cents / 100.0
    }

    fun orders(): List<BillDrinkSummaryOutputMapper> {
        return original.orders().map { it.toMapper() }
    }

    fun discountInPercent(): Int {
        return (original.discount() * 100).roundToInt()
    }
}

fun BillOutput.toMapper(): BillOutputMapper {
    return BillOutputMapper(this)
}
