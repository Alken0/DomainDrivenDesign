package com.bartlin.app.controllers

import com.bartlin.app.CREATE_ORDER
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.CreateOrderInput
import com.bartlin.domain.services.DrinkService
import com.bartlin.domain.services.OrderService
import com.bartlin.domain.services.TableService
import com.bartlin.domain.vo.toId
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.createOrder(tables: TableService, drinks: DrinkService, orders: OrderService) {
    get {
        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Create a new Order"
            }
            content {
                form(method = FormMethod.post) {
                    div("row") {
                        label("form-label") {
                            +"Table"
                            select("form-select") {
                                name = "table"
                                for (table in tables.findAll()) {
                                    option {
                                        value = "${table.id}"
                                        +table.name.toString()
                                    }
                                }
                            }
                        }
                    }

                    div("row") {
                        label("form-label") {
                            +"Drink"
                            select("form-select") {
                                name = "drink"
                                for (drink in drinks.findAll()) {
                                    option {
                                        value = "${drink.id}"
                                        +drink.name.toString()
                                    }
                                }
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
        val data = call.receiveParameters().toCreateOrder()
        orders.create(data)
        call.respondRedirect(CREATE_ORDER)
    }
}

private fun Parameters.toCreateOrder(): CreateOrderInput {
    return CreateOrderInput(
        tableId = this["table"]!!.toId(),
        drinkId = this["drink"]!!.toId(),
    )
}




