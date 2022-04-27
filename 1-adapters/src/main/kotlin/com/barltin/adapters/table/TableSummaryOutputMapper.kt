package com.barltin.adapters.table

import com.bartlin.application.table.TableSummaryOutput

data class TableSummaryOutputMapper(
    val id: String,
    val name: String
)

fun TableSummaryOutput.toMapper(): TableSummaryOutputMapper {
    return TableSummaryOutputMapper(
        id = this.id.value.toString(),
        name = this.name.value
    )
}
