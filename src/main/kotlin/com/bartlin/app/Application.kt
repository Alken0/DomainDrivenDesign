package com.bartlin.app

import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.services.TableService
import com.bartlin.infrastructure.db.DatabaseFactory
import com.bartlin.infrastructure.db.repositories.DrinkRepositoryImpl
import com.bartlin.infrastructure.db.repositories.OrderRepositoryImpl
import com.bartlin.infrastructure.db.repositories.TableRepositoryImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level

fun main() {
	DatabaseFactory.init()

	val drinkRepo = DrinkRepositoryImpl()
	val tableRepo = TableRepositoryImpl()
	val orderRepo = OrderRepositoryImpl()

	val drinkService = DrinkService(drinkRepo)
	val tableService = TableService(tableRepo)
	val orderService = OrderService(drinkRepo, tableRepo, orderRepo)

	embeddedServer(Netty, watchPaths = listOf(), port = 8080, host = "127.0.0.1") {
		install(CallLogging) { level = Level.INFO }
		routes(drinkService, orderService, tableService)
	}.start(wait = true)
}
