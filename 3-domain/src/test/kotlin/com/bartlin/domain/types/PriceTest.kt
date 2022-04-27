package com.bartlin.domain.types

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals


internal class PriceTest {
    @Test
    fun hasToBePositive() {
        assertThrows<IllegalArgumentException> {
            Price(-1)
        }
    }

    @Test
    fun zeroIsAllowed() {
        Price(0)
    }

    @Test
    fun toStringEqualsValue() {
        assertEquals(Price(123).toString(), "123")
    }
}
