package com.bartlin.app

import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.services.ReservationService
import com.bartlin.domain.services.TableService
import com.bartlin.infrastructure.db.DatabaseFactory
import com.bartlin.infrastructure.db.repositories.DrinkRepositoryExposed
import com.bartlin.infrastructure.db.repositories.OrderRepositoryExposed
import com.bartlin.infrastructure.db.repositories.ReservationRepositoryExposed
import com.bartlin.infrastructure.db.repositories.TableRepositoryExposed
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level

fun main() {
    DatabaseFactory.inMemory()

    val drinkRepo = DrinkRepositoryExposed()
    val tableRepo = TableRepositoryExposed()
    val orderRepo = OrderRepositoryExposed()
    val reservationRepo = ReservationRepositoryExposed()

    DummyDataInserter(drinkRepo, tableRepo, reservationRepo).insert()

    val drinkService = DrinkService(drinkRepo)
    val tableService = TableService(tableRepo)
    val orderService = OrderService(drinkRepo, tableRepo, orderRepo)
    val reservationService = ReservationService(reservationRepo)

    embeddedServer(Netty, watchPaths = listOf(), port = 8080, host = "127.0.0.1") {
        install(CallLogging) { level = Level.INFO }
        routes(drinkService, orderService, tableService, reservationService)
    }.start(wait = true)
}
