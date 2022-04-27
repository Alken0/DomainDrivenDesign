package com.barltin.adapters.drink

import com.bartlin.application.drink.UpdateDrinkInput
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class UpdateDrinkInputMapper(
    val id: Long,
    var name: String? = null,
    var price: Long? = null,
    var description: String? = null
) {
    constructor(
        id: String?,
        name: String? = null,
        price: String? = null,
        description: String? = null
    ) : this(
        id = id?.toLong() ?: throw IllegalArgumentException("id is required"),
        name = name,
        price = price?.toLong(),
        description = description
    )

    fun toUpdateDrinkInput(): UpdateDrinkInput {
        return UpdateDrinkInput(
            id = Id(id),
            name = name?.ifBlank { null }?.let { Name(it) },
            price = price?.let { Price(it) },
            description = description?.ifBlank { null }?.let { Description(it) }
        )
    }
}
