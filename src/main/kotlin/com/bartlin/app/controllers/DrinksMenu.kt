package com.bartlin.app.controllers

import com.bartlin.app.UPDATE_DRINK
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

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
						th { +"" }
					}
					for (item in service.findAll()) {
						tr {
							td { +"${item.id}" }
							td { +item.name }
							td { +"${item.price / 100.0}€" }
							td { +item.description }
							td {
								a(href = UPDATE_DRINK.replace("{id}", "${item.id}")) {
									+"edit"
								}
							}
						}
					}
				}
			}
		}
	}
}
