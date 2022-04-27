package com.bartlin.application.table

import com.bartlin.domain.table.Table
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name

data class TableSummaryOutput(
    val id: Id,
    val name: Name
) {
    constructor(table: Table) : this(
        id = table.id,
        name = table.name
    )
}
