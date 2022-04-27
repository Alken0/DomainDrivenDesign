package com.bartlin.plugins.web.controllers.drinks

import com.barltin.adapters.drink.UpdateDrinkInputMapper
import com.barltin.adapters.drink.toMapper
import com.barltin.adapters.values.toId
import com.bartlin.application.drink.DrinkService
import com.bartlin.application.drink.UpdateDrinkInput
import com.bartlin.plugins.web.MENU
import com.bartlin.plugins.web.templates.BaseTemplate
import com.bartlin.plugins.web.util.getId
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

internal fun Route.updateDrink(service: DrinkService) {
    get {
        val id = call.parameters.getId().toId()
        val original = service.findById(id).toMapper()

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
                                value = original.name
                            }
                        }
                    }

                    div("row") {
                        label("form-label") {
                            +"Price (in cents)"
                            numberInput(name = "price", classes = "form-control") {
                                value = "${original.priceInCents}"
                            }
                        }
                    }

                    div("row") {
                        label("form-label") {
                            +"Description"
                            textArea(classes = "form-control") {
                                name = "description"
                                +original.description
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
        val id = call.parameters.getId()
        val data = call.receiveParameters().toUpdateDrink(id)
        service.update(data)
        call.respondRedirect(MENU)
    }
}

private fun Parameters.toUpdateDrink(id: String?): UpdateDrinkInput {
    return UpdateDrinkInputMapper(
        id = id,
        name = this["name"],
        price = this["price"],
        description = this["description"]
    ).toUpdateDrinkInput()
}

