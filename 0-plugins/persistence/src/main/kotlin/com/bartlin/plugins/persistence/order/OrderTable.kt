package com.bartlin.plugins.persistence.order

import com.bartlin.plugins.persistence.drink.DrinkTable
import com.bartlin.plugins.persistence.table.TableTable
import org.jetbrains.exposed.sql.Table

internal object OrderTable : Table() {
    val id = integer("id").autoIncrement()
    val drink = reference("drinkId", DrinkTable.id)
    val table = reference("tableId", TableTable.id)

    override val primaryKey = PrimaryKey(id)
}
