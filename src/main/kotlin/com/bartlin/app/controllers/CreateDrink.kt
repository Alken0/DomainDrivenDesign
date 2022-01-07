package com.bartlin.app.controllers

import com.bartlin.app.CREATE_DRINK
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.CreateDrinkInput
import com.bartlin.domain.services.DrinkService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.createDrink(service: DrinkService) {
	get {
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"Create a new Drink"
			}
			content {
				form(method = FormMethod.post) {
					label {
						+"Name"
						textInput(name = "name")
						br
					}
					label {
						+"Price (in cents)"
						numberInput(name = "price")
						br
					}
					label {
						+"Description"
						textInput(name = "description")
						br
					}
					submitInput()
				}
			}
		}
	}
	
	post {
		val data = call.receiveParameters().toCreateDrinkInput()
		service.create(data)
		call.respondRedirect(CREATE_DRINK)
	}
}

private fun Parameters.toCreateDrinkInput(): CreateDrinkInput {
	return CreateDrinkInput(
		name = this["name"]!!,
		price = this["price"]!!.toInt(),
		description = this["description"]!!
	)
}

