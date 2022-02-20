package com.bartlin.domain.services

import com.bartlin.domain.dto.tables.TableSummaryOutput
import com.bartlin.domain.dto.tables.UpdateTableInput
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import io.ktor.features.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TableServiceTest {
    private val tables = mockk<TableRepository>()
    private val service = TableService(tables)

    @Test
    fun findByIdReturnsCorrectElement() {
        val expected = Table(Id(12), Name("name"))

        every { tables.findById(expected.id) } returns expected

        val result = service.findById(expected.id)

        assertEquals(TableSummaryOutput(expected), result)
        verify(exactly = 1) { tables.findById(expected.id) }
    }

    @Test
    fun findByIdThrowsExceptionIfIdDoesNotExist() {
        val expected = Table(Id(12), Name("name"))

        every { tables.findById(expected.id) } returns null

        assertThrows<NotFoundException> { service.findById(expected.id) }
        verify(exactly = 1) { tables.findById(expected.id) }
    }

    @Test
    fun updateThrowsExceptionIfIdDoesNotExist() {
        val expected = UpdateTableInput(Id(12), Name("name"))

        every { tables.findById(expected.id) } returns null

        assertThrows<NotFoundException> { service.update(expected) }
        verify(exactly = 1) { tables.findById(expected.id) }
    }

    @Test
    fun updateWorks() {
        val input = UpdateTableInput(Id(22), Name("new"))
        val original = Table(Id(22), Name("old"))
        val updated = Table(Id(22), Name("new"))

        every { tables.findById(input.id) } returns original
        every { tables.update(updated) } returns Unit

        service.update(input)

        verify(exactly = 1) { tables.findById(input.id) }
        verify(exactly = 1) { tables.update(updated) }
    }

    @Test
    fun findAllWorks() {
        val output = listOf(Table(Id(22), Name("Table 22")))
        val expected = listOf(TableSummaryOutput(Id(22), Name("Table 22")))

        every { tables.findAll() } returns output

        val result = service.findAll()

        assertEquals(expected, result)
        verify(exactly = 1) { tables.findAll() }
    }
}
