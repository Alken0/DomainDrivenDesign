package com.bartlin.plugins.persistence.drink

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

internal object DrinkTable : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 2000)
    val price = long("price")
    val description = varchar("description", 16000)

    override val primaryKey = PrimaryKey(id)
}

internal fun ResultRow.toDrink() = Drink(
    id = Id(this[DrinkTable.id]),
    name = Name(this[DrinkTable.name]),
    price = Price(this[DrinkTable.price]),
    description = Description(this[DrinkTable.description])
)
