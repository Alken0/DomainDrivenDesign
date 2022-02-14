package com.bartlin.app.controllers

import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.OrderService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.bill(service: OrderService) {
	get {
		val tableId = call.parameters["id"]!!.toInt()
		val drinks = service.findDrinksByTable(tableId)

		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Bill"
			}
			content {
				table("table") {
					thead {
						tr {
							th { scope = ThScope.col; +"Name" }
							th { scope = ThScope.col; +"Price" }
						}
					}
					tbody {
						for (item in drinks) {
							tr {
								td { +item.name }
								td { +"${item.price / 100.0}€" }
							}
						}
					}
					tfoot {
						tr {
							th { +"Total" }
							th { +"${drinks.sumOf { it.price } / 100.0} €" }
						}
					}
				}
			}
		}
	}
}
