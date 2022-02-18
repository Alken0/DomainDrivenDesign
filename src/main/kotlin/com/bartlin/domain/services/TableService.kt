package com.bartlin.domain.services

import com.bartlin.domain.dto.TableSummaryOutput
import com.bartlin.domain.repositories.TableRepository

class TableService(val tables: TableRepository) {
    fun findAll(): List<TableSummaryOutput> {
        return tables.findAll().map { TableSummaryOutput(it) }
    }
}
