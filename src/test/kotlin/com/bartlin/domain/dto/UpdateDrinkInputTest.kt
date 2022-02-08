package com.bartlin.domain.dto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UpdateDrinkInputTest {
	@Test
	fun onlyIdIsRequired() {
		UpdateDrinkInput(id = 123)
	}
	
	@Test
	fun priceHasToBePositive() {
		assertThrows<IllegalArgumentException> {
			UpdateDrinkInput(id = 123, price = -12)
		}
	}
	
	@Test
	fun nameCannotBeBlank() {
		assertThrows<IllegalArgumentException> {
			UpdateDrinkInput(id = 123, name = "")
		}
	}
}
