package com.bartlin.domain.services

import com.bartlin.domain.dto.CreateTableInput
import com.bartlin.domain.dto.TableSummaryOutput
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id

class TableService(val tables: TableRepository) {
    fun findAll(): List<TableSummaryOutput> {
        return tables.findAll().map { TableSummaryOutput(it) }
    }

    fun create(input: CreateTableInput) {
        val data = Table(id = Id(0), name = input.name)
        tables.create(data)
    }
}
