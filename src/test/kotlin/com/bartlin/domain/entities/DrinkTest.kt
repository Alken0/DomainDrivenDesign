package com.bartlin.domain.entities

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class DrinkTest {
	
	@Test
	fun idHasToBePositive() {
		assertThrows<IllegalArgumentException> {
			Drink(id = -1, name = "a", price = 100)
		}
	}
	
	@Test
	fun priceHasToBePositive() {
		assertThrows<IllegalArgumentException> {
			Drink(id = 10, name = "a", price = -1)
		}
	}
	
	@Test
	fun nameCannotBeBlank() {
		assertThrows<IllegalArgumentException> {
			Drink(id = 10, name = "", price = 100)
		}
		assertThrows<IllegalArgumentException> {
			Drink(id = 10, name = "a", price = 100).copy(name = "")
		}
		assertThrows<IllegalArgumentException> {
			Drink(id = 10, name = " ", price = 100)
		}
	}
	
	@Test
	fun defaultDescriptionIsBlank() {
		val default = Drink(id = 10, name = "a", price = 100).description
		assertEquals("", default)
	}
}
