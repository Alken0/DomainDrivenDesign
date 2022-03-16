package com.bartlin.domain.types

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DescriptionTest {
    @Test
    fun toStringEqualsValue() {
        assertEquals(Description("value").toString(), "value")
    }
}
