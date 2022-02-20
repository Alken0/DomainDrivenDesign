package com.bartlin.domain.dto.bills

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price

data class BillDrinkSummaryOutput(
    val name: Name,
    val price: Price
) {
    constructor(input: Drink) : this(input.name, input.price)
}
