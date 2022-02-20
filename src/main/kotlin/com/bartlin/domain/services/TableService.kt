package com.bartlin.domain.services

import com.bartlin.domain.dto.CreateTableInput
import com.bartlin.domain.dto.TableSummaryOutput
import com.bartlin.domain.dto.UpdateTableInput
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id
import io.ktor.features.*

class TableService(val tables: TableRepository) {
    fun findById(id: Id): TableSummaryOutput {
        val table = tables.findById(id) ?: throw NotFoundException("table not found")
        return TableSummaryOutput(table)
    }

    fun update(input: UpdateTableInput) {
        val original = tables.findById(input.id) ?: throw NotFoundException("table not found")
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
