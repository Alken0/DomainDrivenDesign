package com.bartlin.infrastructure.db.tables

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object DrinkTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 2000)
    val price = integer("price")
    val description = varchar("description", 16000)

    override val primaryKey = PrimaryKey(id)
}

fun ResultRow.toDrink() = Drink(
    id = Id(this[DrinkTable.id]),
    name = Name(this[DrinkTable.name]),
    price = Price(this[DrinkTable.price]),
    description = this[DrinkTable.description]
)
