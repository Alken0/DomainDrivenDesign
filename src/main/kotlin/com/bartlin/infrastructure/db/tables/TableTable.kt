package com.bartlin.infrastructure.db.tables

import com.bartlin.domain.vo.Id
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object TableTable : Table() {
    val id = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id)
}

fun ResultRow.toTable() = com.bartlin.domain.entities.Table(
    id = Id(this[TableTable.id])
)
