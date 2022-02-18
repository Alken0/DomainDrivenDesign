package com.bartlin.domain.services

import com.bartlin.domain.dto.DrinkSummaryOutput
import com.bartlin.domain.dto.UpdateDrinkInput
import com.bartlin.domain.entities.Drink
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price
import io.ktor.features.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class DrinkServiceTest {
    private val drinks = mockk<DrinkRepository>()
    private val service = DrinkService(drinks)

    @Test
    fun findByIdReturnsCorrectElement() {
        val expected = Drink(Id(12), Name("name"), Price(12), "")

        every { drinks.findById(expected.id) } returns expected

        val result = service.findById(expected.id)

        assertEquals(DrinkSummaryOutput(expected), result)
        verify(exactly = 1) { drinks.findById(expected.id) }
    }

    @Test
    fun findByIdThrowsExceptionIfIdDoesNotExist() {
        val expected = Drink(Id(12), Name("name"), Price(12), "")

        every { drinks.findById(expected.id) } returns null

        assertThrows<NotFoundException> { service.findById(expected.id) }
        verify(exactly = 1) { drinks.findById(expected.id) }
    }

    @Test
    fun updateThrowsExceptionIfIdDoesNotExist() {
        val expected = UpdateDrinkInput(Id(12), Name("name"), Price(12), "")

        every { drinks.findById(expected.id) } returns null

        assertThrows<NotFoundException> { service.update(expected) }
        verify(exactly = 1) { drinks.findById(expected.id) }
    }

    @Test
    fun updatePartially() {
        val input = UpdateDrinkInput(Id(22), Name("new"))
        val original = Drink(Id(22), Name("new"), Price(12), "")
        val updated = Drink(Id(22), Name("new"), Price(12), description = "")

        every { drinks.findById(input.id) } returns original
        every { drinks.update(updated) } returns Unit

        service.update(input)

        verify(exactly = 1) { drinks.findById(input.id) }
        verify(exactly = 1) { drinks.update(updated) }
    }

    @Test
    fun findAll() {
        val output = listOf(Drink(Id(22), Name("name"), Price(12), ""))
        val expected = listOf(DrinkSummaryOutput(Id(22), Name("name"), Price(12), ""))

        every { drinks.findAll() } returns output

        val result = service.findAll()

        assertEquals(expected, result)
        verify(exactly = 1) { drinks.findAll() }
    }
}
