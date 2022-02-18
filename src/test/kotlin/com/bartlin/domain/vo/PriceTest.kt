package com.bartlin.domain.vo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


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
}
