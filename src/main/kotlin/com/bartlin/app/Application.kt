package com.bartlin.app

import com.bartlin.infrastructure.db.DatabaseFactory
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.title
import org.slf4j.event.Level

fun main() {
	DatabaseFactory.init()

	embeddedServer(Netty, watchPaths = listOf(), port = 8080, host = "127.0.0.1") {
		install(CallLogging) { level = Level.INFO }

		module()
	}.start(wait = true)
}

	routing {
		route("/") { index() }
	}
}

fun Route.index() {
	get {
		call.respondHtml {
			head {
				title("Hello World")
			}
			body {
				div {
					+"Hello World"
				}
			}
		}
	}
}

