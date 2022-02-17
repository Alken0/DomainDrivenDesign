package com.bartlin.infrastructure.db.tables

import org.jetbrains.exposed.sql.Table

object OrderTable : Table() {
	val id = integer("id").autoIncrement()
	val drink = reference("drinkId", DrinkTable.id)
	val table = reference("tableId", TableTable.id)

	override val primaryKey = PrimaryKey(id)
}
