package com.bartlin.domain.vo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
}
