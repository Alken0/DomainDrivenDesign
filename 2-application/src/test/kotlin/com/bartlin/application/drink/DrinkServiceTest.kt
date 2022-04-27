package com.bartlin.application.drink

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.types.Description
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Price
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
        val expected = Drink(Id(12), Name("name"), Price(12), Description(""))

        every { drinks.findById(expected.id) } returns expected

        val result = service.findById(expected.id)

        assertEquals(DrinkSummaryOutput(expected), result)
        verify(exactly = 1) { drinks.findById(expected.id) }
    }

    @Test
    fun findByIdThrowsExceptionIfIdDoesNotExist() {
        val expected = Drink(Id(12), Name("name"), Price(12), Description(""))

        every { drinks.findById(expected.id) } returns null

        assertThrows<NoSuchElementException> { service.findById(expected.id) }
        verify(exactly = 1) { drinks.findById(expected.id) }
    }

    @Test
    fun updateThrowsExceptionIfIdDoesNotExist() {
        val expected = UpdateDrinkInput(Id(12), Name("name"), Price(12), Description(""))

        every { drinks.findById(expected.id) } returns null

        assertThrows<NoSuchElementException> { service.update(expected) }
        verify(exactly = 1) { drinks.findById(expected.id) }
    }

    @Test
    fun updatePartially() {
        val input = UpdateDrinkInput(Id(22), Name("new"))
        val original = Drink(Id(22), Name("new"), Price(12), Description(""))
        val updated = Drink(Id(22), Name("new"), Price(12), description = Description(""))

        every { drinks.findById(input.id) } returns original
        every { drinks.update(updated) } returns Unit

        service.update(input)

        verify(exactly = 1) { drinks.findById(input.id) }
        verify(exactly = 1) { drinks.update(updated) }
    }

    @Test
    fun findAll() {
        val output = listOf(Drink(Id(22), Name("name"), Price(12), Description("")))
        val expected = listOf(DrinkSummaryOutput(Id(22), Name("name"), Price(12), Description("")))

        every { drinks.findAll() } returns output

        val result = service.findAll()

        assertEquals(expected, result)
        verify(exactly = 1) { drinks.findAll() }
    }
}
