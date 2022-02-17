package com.bartlin.infrastructure.db.repositories

import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id
import com.bartlin.infrastructure.db.tables.TableTable
import com.bartlin.infrastructure.db.tables.toTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TableRepositoryImpl : TableRepository {
	override fun findAll(): List<Table> {
		return transaction {
			TableTable.selectAll().sortedBy { TableTable.id }.toList()
				.map { it.toTable() }
		}
	}

	override fun findById(id: Id): Table? {
		return transaction {
			TableTable.select { TableTable.id eq id.toInt() }.first()?.toTable()
		}
	}
}
