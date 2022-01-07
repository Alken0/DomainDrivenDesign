package com.bartlin.infrastructure.db.tables

import org.jetbrains.exposed.sql.Table

object DrinkTable : Table() {
	val id = integer("id").autoIncrement()
	val name = varchar("name", 2000)
	val price = integer("price")
	val description = varchar("description", 16000)
	
	override val primaryKey = PrimaryKey(id)
}
