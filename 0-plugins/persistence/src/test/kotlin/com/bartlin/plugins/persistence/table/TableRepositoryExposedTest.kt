package com.bartlin.plugins.persistence.table

import com.bartlin.domain.table.Table
import com.bartlin.domain.table.TableRepository
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.plugins.persistence.DatabaseTest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TableRepositoryExposedTest : DatabaseTest(arrayOf(TableTable)) {
    private val repository: TableRepository = TableRepositoryExposed()

    @Test
    fun findAllWorks() {
        transaction {
            for (i in 0..9) {
                TableTable.insert {
                    it[this.name] = "Table $i"
                }
            }
        }

        val result = repository.findAll()

        assertEquals(10, result.size)
    }

    @Test
    fun findByIdWorks() {
        transaction {
            TableTable.insert {
                it[this.name] = "Table 1"
            }
        }

        val result = repository.findById(Id(1))

        assertEquals(Table(Id(1), Name("Table 1")), result)
    }

    @Test
    fun findByIdWorksReturnsNullIfNothingFound() {
        val result = repository.findById(Id(1))

        assertEquals(null, result)
    }
}
