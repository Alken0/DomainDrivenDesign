package com.bartlin.app.controllers

import com.bartlin.app.UPDATE_DRINK
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.getMenu(service: DrinkService) {
	get {
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Menu"
			}
			content {
				table("table") {
					thead {
						tr {
							th { scope = ThScope.col; +"Number" }
							th { scope = ThScope.col; +"Name" }
							th { scope = ThScope.col; +"Price" }
							th { scope = ThScope.col; +"Description" }
							th { scope = ThScope.col; +"" }
						}
					}
					tbody {
						for (item in service.findAll()) {
							tr {
								th { scope = ThScope.row; +item.id.toString() }
								td { +item.name.toString() }
								td { +item.price.toEuro() }
								td { +item.description }
								td {
									a(href = UPDATE_DRINK.replace("{id}", item.id.toString())) {
										button(classes = "btn btn-secondary") {
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
	}
}
