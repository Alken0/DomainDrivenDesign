package com.bartlin.app.controllers

import com.bartlin.app.ORDER
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.CreateOrderInput
import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.services.TableService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.order(tables: TableService, drinks: DrinkService, orders: OrderService) {
	get {
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Create a new Order"
			}
			content {
				form(method = FormMethod.post) {
					label {
						+"Table"
						select {
							name = "table"
							for (table in tables.findAll()) {
								option {
									value = "${table.id}"
									+table.name
								}
							}
						}
					}
					br
					label {
						+"Drink"
						select {
							name = "drink"
							for (drink in drinks.findAll()) {
								option {
									value = "${drink.id}"
									+drink.name
								}
							}
						}
					}
					br
					submitInput()
				}
			}
		}
	}

	post {
		val data = call.receiveParameters().toCreateOrder()
		orders.create(data)
		call.respondRedirect(ORDER)
	}
}

private fun Parameters.toCreateOrder(): CreateOrderInput {
	return CreateOrderInput(
		tableId = this["table"]!!.toInt(),
		drinkId = this["drink"]!!.toInt(),
	)
}




