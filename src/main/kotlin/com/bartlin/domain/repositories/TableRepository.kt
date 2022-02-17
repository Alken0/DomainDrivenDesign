package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Table
import com.bartlin.domain.vo.Id

interface TableRepository {
	fun findAll(): List<Table>
	fun findById(id: Id): Table?
}
