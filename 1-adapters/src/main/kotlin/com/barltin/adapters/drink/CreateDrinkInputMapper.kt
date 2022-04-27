package com.barltin.adapters.drink

import com.bartlin.application.drink.CreateDrinkInput
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price

data class CreateDrinkInputMapper(
    val name: String,
    val price: Long,
    val description: String,
) {
    constructor(name: String?, price: String?, description: String?) : this(
        name = name ?: throw IllegalArgumentException("name is required"),
        price = price?.toLong() ?: throw IllegalArgumentException("price is required and has to be a number"),
        description = description ?: throw IllegalArgumentException("description is required")
    )

    fun toCreateDrinkInput(): CreateDrinkInput {
        return CreateDrinkInput(
            name = Name(this.name),
            price = Price(this.price),
            description = Description(this.description)
        )
    }
}
