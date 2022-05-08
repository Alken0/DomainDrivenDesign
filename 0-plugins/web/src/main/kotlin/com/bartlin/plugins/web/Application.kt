package com.bartlin.plugins.web

import com.bartlin.application.drink.DrinkService
import com.bartlin.application.order.OrderService
import com.bartlin.application.reservation.ReservationService
import com.bartlin.application.table.TableService
import com.bartlin.plugins.persistence.DatabaseConnector
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level

fun main() {
    val repositoryFactory = DatabaseConnector.inMemory()

    val drinkRepo = repositoryFactory.getDrinkRepository()
    val tableRepo = repositoryFactory.getTableRepository()
    val orderRepo = repositoryFactory.getOrderRepository()
    val reservationRepo = repositoryFactory.getReservationRepository()

    DummyDataInserter(drinkRepo, tableRepo, reservationRepo).insert()

    val drinkService = DrinkService(drinkRepo)
    val tableService = TableService(tableRepo)
    val orderService = OrderService(drinkRepo, tableRepo, orderRepo)
    val reservationService = ReservationService(reservationRepo)

    val host: String = System.getenv("BARTLIN_HOST") ?: "127.0.0.1"
    embeddedServer(Netty, watchPaths = listOf(), port = 8080, host = host) {
        install(CallLogging) { level = Level.INFO }
        routes(drinkService, orderService, tableService, reservationService)
    }.start(wait = true)
}
