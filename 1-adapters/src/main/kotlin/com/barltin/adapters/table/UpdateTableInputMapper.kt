package com.barltin.adapters.table

import com.bartlin.application.table.UpdateTableInput
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name

data class UpdateTableInputMapper(
    val id: Long,
    val name: String?
) {
    constructor(id: String?, name: String?) : this(
        id = id?.toLong() ?: throw IllegalArgumentException("id is required"),
        name = name
    )

    fun toUpdateTableInput(): UpdateTableInput {
        return UpdateTableInput(
            id = Id(id),
            name = name?.ifBlank { null }?.let { Name(it) }
        )
    }
}
