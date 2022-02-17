package com.bartlin.app.controllers

import com.bartlin.app.MENU
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.UpdateDrinkInput
import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.toId
import com.bartlin.domain.vo.toName
import com.bartlin.domain.vo.toPrice
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.updateDrink(service: DrinkService) {
	get {
		val id = call.parameters["id"]!!.toId()
		val original = service.findById(id)
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Update Drink Data"
			}
			content {
				form(method = FormMethod.post) {
					div("row") {
						label("form-label") {
							+"Name"
							textInput(name = "name", classes = "form-control") {
								placeholder = original.name.toString()
							}
						}
					}
					
					div("row") {
						label("form-label") {
							+"Price (in cents)"
							numberInput(name = "price", classes = "form-control") {
								placeholder = original.price.toEuro()
							}
						}
					}
					
					div("row") {
						label("form-label") {
							+"Description"
							textArea(classes = "form-control") {
								name = "description"
								placeholder = original.description
							}
						}
					}
					
					div("row mx-auto") {
						button(classes = "btn btn-primary") {
							type = ButtonType.submit
							+"Submit"
						}
					}
				}
			}
		}
	}
	
	post {
		val id = call.parameters["id"]!!.toId()
		val data = call.receiveParameters().toUpdateDrink(id)
		service.update(data)
		call.respondRedirect(MENU)
	}
}

private fun Parameters.toUpdateDrink(id: Id): UpdateDrinkInput {
	return UpdateDrinkInput(
		id = id,
		name = this["name"]?.ifBlank { null }?.toName(),
		price = this["price"]?.ifBlank { null }?.toPrice(),
		description = this["description"]?.ifBlank { null }
	)
}

