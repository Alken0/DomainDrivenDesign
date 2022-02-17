package com.bartlin.infrastructure.db.tables

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object TableTable : Table() {
	val id = integer("id").autoIncrement()
	override val primaryKey = PrimaryKey(id)
}

fun ResultRow.toTable() = com.bartlin.domain.entities.Table(
	id = this[TableTable.id]
)
