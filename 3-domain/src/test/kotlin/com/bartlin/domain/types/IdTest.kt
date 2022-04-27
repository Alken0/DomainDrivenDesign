package com.bartlin.domain.types

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class IdTest {
    @Test
    fun hasToBePositive() {
        assertThrows<IllegalArgumentException> {
            Id(-1)
        }
    }

    @Test
    fun zeroIsAllowed() {
        Id(0)
    }

    @Test
    fun toStringEqualsValue() {
        assertEquals(Id(123).toString(), "123")
    }
}
