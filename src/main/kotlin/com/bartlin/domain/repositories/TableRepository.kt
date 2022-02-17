package com.bartlin.domain.repositories

import com.bartlin.domain.entities.Table

interface TableRepository {
	fun findAll(): List<Table>
	fun findById(id: Int): Table?
}
