package com.bartlin.plugins.persistence.table

import com.bartlin.domain.table.Table
import com.bartlin.domain.table.TableRepository
import com.bartlin.domain.types.Id
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

internal class TableRepositoryExposed : TableRepository {
    override fun findAll(): List<Table> {
        return transaction {
            TableTable.selectAll().sortedBy { TableTable.id }.toList()
                .map { it.toTable() }
        }
    }

    override fun findById(id: Id): Table? {
        return try {
            transaction {
                TableTable.select { TableTable.id eq id.value }.first().toTable()
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun create(input: Table) {
        transaction {
            TableTable.insert {
                it[this.name] = input.name.value
            }
        }
    }

    override fun update(input: Table) {
        transaction {
            TableTable.update({ TableTable.id eq input.id.value }) {
                it[this.name] = input.name.value
            }
        }
    }
}
