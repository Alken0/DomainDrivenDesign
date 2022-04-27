package com.bartlin.application.table

import com.bartlin.domain.table.Table
import com.bartlin.domain.table.TableRepository
import com.bartlin.domain.types.Id

class TableService(val tables: TableRepository) {
    fun findById(id: Id): TableSummaryOutput {
        val table = tables.findById(id) ?: throw NoSuchElementException("table not found")
        return TableSummaryOutput(table)
    }

    fun update(input: UpdateTableInput) {
        val original = tables.findById(input.id) ?: throw NoSuchElementException("table not found")
        val updated = original.copy(
            name = input.name ?: original.name
        )
        tables.update(updated)
    }

    fun findAll(): List<TableSummaryOutput> {
        return tables.findAll().map { TableSummaryOutput(it) }
    }

    fun create(input: CreateTableInput) {
        val data = Table(id = Id(0), name = input.name)
        tables.create(data)
    }
}
