package com.bartlin.app

import com.bartlin.domain.services.DrinkService
import com.bartlin.infrastructure.db.DatabaseFactory
import com.bartlin.infrastructure.db.repositories.DrinkRepositoryImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level

fun main() {
	DatabaseFactory.init()
	
	val drinkService = DrinkService(DrinkRepositoryImpl())
	
	embeddedServer(Netty, watchPaths = listOf(), port = 8080, host = "127.0.0.1") {
		install(CallLogging) { level = Level.INFO }
		routes(drinkService)
	}.start(wait = true)
}
