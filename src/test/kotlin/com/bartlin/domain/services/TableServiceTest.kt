package com.bartlin.domain.services

import com.bartlin.domain.dto.TableSummaryOutput
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TableServiceTest {
	private val tables = mockk<TableRepository>()
	private val service = TableService(tables)

	@Test
	fun findAllWorks() {
		val output = listOf(Table(Id(22)))
		val expected = listOf(TableSummaryOutput(Id(22), Name("Table 22")))

		every { tables.findAll() } returns output

		val result = service.findAll()

		assertEquals(expected, result)
		verify(exactly = 1) { tables.findAll() }
	}
}
