package com.barltin.adapters.table

import com.bartlin.application.table.CreateTableInput
import com.bartlin.domain.types.Name

data class CreateTableInputMapper(
    val name: String?
) {
    /* TODO not possbile because of jvm?
    constructor(name: String?) : this(
        name = name ?: throw IllegalArgumentException("name is required")
    )
    */

    fun toCreateTableInput(): CreateTableInput {
        return CreateTableInput(
            name = Name(name ?: throw IllegalArgumentException("name is required"))
        )
    }
}
