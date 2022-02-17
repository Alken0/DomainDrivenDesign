package com.bartlin.domain.dto

import com.bartlin.domain.vo.Id
import org.junit.jupiter.api.Test

internal class UpdateDrinkInputTest {
	@Test
	fun onlyIdIsRequired() {
		UpdateDrinkInput(id = Id(1))
	}
}
