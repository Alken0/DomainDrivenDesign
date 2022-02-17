package com.bartlin.app

import com.bartlin.app.controllers.*
import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.services.TableService
import io.ktor.application.*
import io.ktor.routing.*

const val INDEX = "/"
const val CREATE_DRINK = "/drink"
const val UPDATE_DRINK = "/drinks/{id}"
const val GET_DRINKS_MENU = "/menu"
const val ORDER = "/order"

fun Application.routes(drinkService: DrinkService, orderService: OrderService, tableService: TableService) {
	routing {
		route(INDEX) { index() }
		route(CREATE_ORDER) { createOrder(tableService, drinkService, orderService) }
		route(CREATE_DRINK) { createDrink(drinkService) }
		route(UPDATE_DRINK) { updateDrink(drinkService) }
		route(GET_DRINKS_MENU) { getDrinksMenu(drinkService) }
	}
}
