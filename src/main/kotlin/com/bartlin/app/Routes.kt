package com.bartlin.app

import com.bartlin.app.controllers.*
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.routing.*

const val INDEX = "/"
const val CREATE_DRINK = "/drink"

fun Application.routes(drinkService: DrinkService) {
	routing {
		route(INDEX) { index() }
		route(CREATE_DRINK) { createDrink(drinkService) }
	}
}
