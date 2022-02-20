package com.bartlin.app

import com.bartlin.app.controllers.bill
import com.bartlin.app.controllers.drinks.createDrink
import com.bartlin.app.controllers.drinks.updateDrink
import com.bartlin.app.controllers.getMenu
import com.bartlin.app.controllers.index
import com.bartlin.app.controllers.orders.createOrder
import com.bartlin.app.controllers.tables.createTable
import com.bartlin.app.controllers.tables.tables
import com.bartlin.app.controllers.tables.updateTable
import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.services.TableService
import io.ktor.application.*
import io.ktor.routing.*

const val INDEX = "/"
const val CREATE_DRINK = "/drinks/new"
const val UPDATE_DRINK = "/drinks/{id}"
const val MENU = "/menu"
const val CREATE_ORDER = "/orders/new"
const val BILL = "/tables/{id}/bill"
const val TABLES = "/tables"
const val CREATE_TABLE = "/tables/new"
const val UPDATE_TABLE = "/tables/{id}"

fun Application.routes(drinkService: DrinkService, orderService: OrderService, tableService: TableService) {
    routing {
        route(INDEX) { index() }
        route(CREATE_ORDER) { createOrder(tableService, drinkService, orderService) }
        route(CREATE_DRINK) { createDrink(drinkService) }
        route(UPDATE_DRINK) { updateDrink(drinkService) }
        route(MENU) { getMenu(drinkService) }
        route(BILL) { bill(orderService) }
        route(TABLES) { tables(tableService) }
        route(CREATE_TABLE) { createTable(tableService) }
        route(UPDATE_TABLE) { updateTable(tableService) }
    }
}
