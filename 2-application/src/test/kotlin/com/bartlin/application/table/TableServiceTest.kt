package com.bartlin.application.table

import com.bartlin.domain.table.Table
import com.bartlin.domain.table.TableRepository
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

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

        assertThrows<NoSuchElementException> { service.findById(expected.id) }
        verify(exactly = 1) { tables.findById(expected.id) }
    }

    @Test
    fun updateThrowsExceptionIfIdDoesNotExist() {
        val expected = UpdateTableInput(Id(12), Name("name"))

        every { tables.findById(expected.id) } returns null

        assertThrows<NoSuchElementException> { service.update(expected) }
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
