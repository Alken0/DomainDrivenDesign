package com.bartlin.domain.dto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class CreateDrinkInputTest {
	
	@Test
	fun priceHasToBePositive() {
		assertThrows<IllegalArgumentException> {
			CreateDrinkInput(name = "a", price = -1)
		}
	}
	
	@Test
	fun nameCannotBeBlank() {
		assertThrows<IllegalArgumentException> {
			CreateDrinkInput(name = "", price = 100)
		}
		assertThrows<IllegalArgumentException> {
			CreateDrinkInput(name = "a", price = 100).copy(name = "")
		}
		assertThrows<IllegalArgumentException> {
			CreateDrinkInput(name = " ", price = 100)
		}
	}
	
	@Test
	fun defaultDescriptionIsBlank() {
		val default = CreateDrinkInput(name = "a", price = 1).description
		assertEquals("", default)
	}
}
