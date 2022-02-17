package com.bartlin.domain.dto

import com.bartlin.domain.entities.Table

data class TableSummaryOutput(
	val id: Int,
	val name: String
) {
	constructor(table: Table) : this(
		id = table.id,
		name = "Table ${table.id}"
	)
}
