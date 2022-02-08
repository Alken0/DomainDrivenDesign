package com.bartlin.app

import com.bartlin.app.controllers.createDrink
import com.bartlin.app.controllers.getDrinksMenu
import com.bartlin.app.controllers.index
import com.bartlin.app.controllers.updateDrink
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.routing.*

const val INDEX = "/"
const val CREATE_DRINK = "/drink"
const val UPDATE_DRINK = "/drinks/{id}"
const val GET_DRINKS_MENU = "/menu"

fun Application.routes(drinkService: DrinkService) {
	routing {
		route(INDEX) { index() }
		route(CREATE_DRINK) { createDrink(drinkService) }
		route(UPDATE_DRINK) { updateDrink(drinkService) }
		route(GET_DRINKS_MENU) { getDrinksMenu(drinkService) }
	}
}
