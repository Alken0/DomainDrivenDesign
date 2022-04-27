package com.bartlin.domain.types

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class NameTest {
    @Test
    fun cannotBeEmpty() {
        assertThrows<IllegalArgumentException> {
            Name("")
        }
    }

    @Test
    fun cannotBeBlank() {
        assertThrows<IllegalArgumentException> {
            Name(" ")
        }
    }

    @Test
    fun toStringEqualsValue() {
        assertEquals(Name("value").toString(), "value")
    }
}
