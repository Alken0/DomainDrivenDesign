package com.bartlin.plugins.web

import com.bartlin.application.drink.DrinkService
import com.bartlin.application.order.OrderService
import com.bartlin.application.reservation.ReservationService
import com.bartlin.application.table.TableService
import com.bartlin.plugins.web.controllers.bill
import com.bartlin.plugins.web.controllers.drinks.createDrink
import com.bartlin.plugins.web.controllers.drinks.updateDrink
import com.bartlin.plugins.web.controllers.getMenu
import com.bartlin.plugins.web.controllers.index
import com.bartlin.plugins.web.controllers.orders.createOrder
import com.bartlin.plugins.web.controllers.reservations.createReservation
import com.bartlin.plugins.web.controllers.reservations.deleteReservation
import com.bartlin.plugins.web.controllers.reservations.reservations
import com.bartlin.plugins.web.controllers.tables.createTable
import com.bartlin.plugins.web.controllers.tables.tables
import com.bartlin.plugins.web.controllers.tables.updateTable
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
const val RESERVATIONS = "/reservations"
const val CREATE_RESERVATION = "/reservations/new"
const val DELETE_RESERVATION = "/reservations/{id}/delete"

fun Application.routes(
    drinkService: DrinkService,
    orderService: OrderService,
    tableService: TableService,
    reservationService: ReservationService
) {
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
        route(RESERVATIONS) { reservations(reservationService) }
        route(CREATE_RESERVATION) { createReservation(reservationService, tableService) }
        route(DELETE_RESERVATION) { deleteReservation(reservationService) }
    }
}
