package com.bartlin.app.controllers

import com.bartlin.app.INDEX
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.vo.toId
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.bill(service: OrderService) {
	get {
		val tableId = call.parameters["id"]!!.toId()
		val bill = service.getBillForTable(tableId)

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
						for (item in bill.orders()) {
							tr {
								td { +item.name.toString() }
								td { +item.price.toEuro() }
							}
						}
					}
					tfoot {
						tr {
							th { +"Total" }
							th { +bill.total().toEuro() }
						}
					}
				}
				form(method = FormMethod.post) {
					div("row mx-auto") {
						button(classes = "btn btn-primary") {
							type = ButtonType.submit
							+"Clear"
						}
					}
				}
			}
		}
	}

	post {
		val tableId = call.parameters["id"]!!.toId()
		service.clearTable(tableId)
		call.respondRedirect(INDEX)
	}
}
