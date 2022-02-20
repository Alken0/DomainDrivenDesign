package com.bartlin.domain.services

import com.bartlin.domain.dto.BillOutput
import com.bartlin.domain.dto.CreateOrderInput
import com.bartlin.domain.entities.Drink
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.repositories.OrderRepository
import com.bartlin.domain.repositories.TableRepository
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

internal class OrderServiceTest {
    private val drinks = mockk<DrinkRepository>()
    private val tables = mockk<TableRepository>()
    private val orders = mockk<OrderRepository>()
    private val service = OrderService(drinks, tables, orders)

    @Test
    fun createWorks() {
        val drink = Drink(Id(12), Name("name"), Price(12), "")
        val table = Table(Id(124), Name("Table 124"))
        val input = CreateOrderInput(tableId = table.id, drinkId = drink.id)

        every { drinks.findById(drink.id) } returns drink
        every { tables.findById(table.id) } returns table
        every { orders.create(table, drink) } returns Unit

        service.create(input)

        verify(exactly = 1) { drinks.findById(drink.id) }
        verify(exactly = 1) { tables.findById(table.id) }
        verify(exactly = 1) { orders.create(table, drink) }
    }

    @Test
    fun createThrowsExceptionIfTableNotFound() {
        val drink = Drink(Id(12), Name("name"), Price(12), "")
        val table = Table(Id(124), Name("Table 124"))
        val input = CreateOrderInput(tableId = table.id, drinkId = drink.id)

        every { tables.findById(table.id) } returns null

        assertThrows<NotFoundException> { service.create(input) }
        verify(exactly = 1) { tables.findById(table.id) }
    }

    @Test
    fun createThrowsExceptionIfDrinkNotFound() {
        val drink = Drink(Id(12), Name("name"), Price(12), "")
        val table = Table(Id(124), Name("Table 124"))
        val input = CreateOrderInput(tableId = table.id, drinkId = drink.id)

        every { tables.findById(table.id) } returns table
        every { drinks.findById(drink.id) } returns null

        assertThrows<NotFoundException> { service.create(input) }
        verify(exactly = 1) { drinks.findById(drink.id) }
        verify(exactly = 1) { tables.findById(table.id) }
    }

    @Test
    fun getBillForTableWorks() {
        val table = Table(Id(12), Name("Table 124"))
        val expectedDrinks = listOf(Drink(Id(1), Name("name"), Price(12), ""))
        val expectedBill = BillOutput(expectedDrinks)

        every { tables.findById(table.id) } returns table
        every { orders.findDrinksByTable(table) } returns expectedDrinks

        val result = service.getBillForTable(table.id)

        assertEquals(expectedBill, result)
        verify(exactly = 1) { tables.findById(table.id) }
        verify(exactly = 1) { orders.findDrinksByTable(table) }
    }

    @Test
    fun getBillForTableThrowsExceptionIfTableDoesNotExist() {
        val table = Table(Id(12), Name("Table 124"))

        every { tables.findById(table.id) } returns null

        assertThrows<NotFoundException> { service.getBillForTable(table.id) }
        verify(exactly = 1) { tables.findById(table.id) }
    }

    @Test
    fun clearTableWorks() {
        val table = Table(Id(12), Name("Table 12"))

        every { tables.findById(table.id) } returns table
        every { orders.deleteByTable(table) } returns Unit

        service.clearTable(table.id)

        verify(exactly = 1) { tables.findById(table.id) }
        verify(exactly = 1) { orders.deleteByTable(table) }
    }

    @Test
    fun clearTableThrowsExceptionIfTableNotFound() {
        val table = Table(Id(12), Name("Table 22"))

        every { tables.findById(table.id) } returns null

        assertThrows<NotFoundException> { service.clearTable(table.id) }

        verify(exactly = 1) { tables.findById(table.id) }
    }
}
