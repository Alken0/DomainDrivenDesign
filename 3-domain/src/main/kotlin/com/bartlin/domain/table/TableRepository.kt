package com.bartlin.domain.table

import com.bartlin.domain.types.Id

interface TableRepository {
    fun findAll(): List<Table>
    fun findById(id: Id): Table?
    fun create(input: Table)
    fun update(input: Table)
}
