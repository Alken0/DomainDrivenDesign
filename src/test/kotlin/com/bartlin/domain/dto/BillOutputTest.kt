package com.bartlin.domain.dto

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BillOutputTest {
    private val bill = BillOutput(
        listOf(
            Drink(id = Id(1), name = Name("name"), price = Price(1), description = ""),
            Drink(id = Id(1), name = Name("name1"), price = Price(4), description = ""),
            Drink(id = Id(1), name = Name("name2"), price = Price(12), description = ""),
            Drink(id = Id(1), name = Name("name3"), price = Price(7), description = ""),
            Drink(id = Id(1), name = Name("name4"), price = Price(8), description = "")
        )
    )

    @Test
    fun totalIsCorrect() {
        assertEquals(Price(32), bill.total())
    }

    @Test
    fun ordersAreCorrect() {
        val expected = listOf(
            BillDrinkSummaryOutput(name = Name("name"), price = Price(1)),
            BillDrinkSummaryOutput(name = Name("name1"), price = Price(4)),
            BillDrinkSummaryOutput(name = Name("name2"), price = Price(12)),
            BillDrinkSummaryOutput(name = Name("name3"), price = Price(7)),
            BillDrinkSummaryOutput(name = Name("name4"), price = Price(8)),
        )
        assertEquals(expected, bill.orders())
    }
}
