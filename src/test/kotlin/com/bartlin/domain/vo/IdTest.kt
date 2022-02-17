package com.bartlin.domain.vo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
}
