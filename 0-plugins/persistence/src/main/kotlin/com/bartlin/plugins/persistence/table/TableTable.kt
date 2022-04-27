package com.bartlin.plugins.persistence.table

import com.bartlin.domain.table.Table
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import org.jetbrains.exposed.sql.ResultRow

internal object TableTable : org.jetbrains.exposed.sql.Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 2000)

    override val primaryKey = PrimaryKey(id)
}

internal fun ResultRow.toTable() = Table(
    id = Id(this[TableTable.id]),
    name = Name(this[TableTable.name])
)
