package com.bartlin.app.controllers

import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.th
import kotlinx.html.tr

fun Route.getDrinksMenu(service: DrinkService) {
	get {
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Drinks Menu"
			}
			content {
				table {
					tr {
						th { +"Number" }
						th { +"Name" }
						th { +"Price" }
						th { +"Description" }
					}
					for (item in service.findAll()) {
						tr {
							td { +"${item.id}" }
							td { +item.name }
							td { +"${item.price / 100.0}€" }
							td { +item.description }
						}
					}
				}
			}
		}
	}
}
