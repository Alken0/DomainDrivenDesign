package com.bartlin.app.controllers

import com.bartlin.app.CREATE_DRINK
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.CreateDrinkInput
import com.bartlin.domain.dto.UpdateDrinkInput
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.updateDrink(service: DrinkService) {
	get {
		val id = call.parameters["id"]!!.toInt()
		val original = service.findById(id)
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Update Drink Data"
			}
			content {
				form(method = FormMethod.post) {
					label {
						+"Name"
						textInput(name = "name") {
							+" current: ${original.name}"
						}
						br
					}
					label {
						+"Price (in cents)"
						numberInput(name = "price") {
							+" current: ${original.price} cents"
						}
						br
					}
					label {
						+"Description"
						textInput(name = "description") {
							+" current: ${original.description}"
						}
						br
					}
					submitInput()
				}
			}
		}
	}
	
	post {
		val id = call.parameters["id"]!!.toInt()
		val data = call.receiveParameters().toUpdateDrink(id)
		service.update(data)
		call.respondRedirect(CREATE_DRINK)
	}
}

private fun Parameters.toUpdateDrink(id: Int): UpdateDrinkInput {
	return UpdateDrinkInput(
		id = id,
		name = this["name"],
		price = this["price"]?.toInt(),
		description = this["description"]
	)
}

