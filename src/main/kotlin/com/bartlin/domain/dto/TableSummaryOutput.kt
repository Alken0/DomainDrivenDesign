package com.bartlin.domain.dto

import com.bartlin.domain.entities.Table
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name

data class TableSummaryOutput(
    val id: Id,
    val name: Name
) {
    constructor(table: Table) : this(
        id = table.id,
        name = table.name
    )
}
