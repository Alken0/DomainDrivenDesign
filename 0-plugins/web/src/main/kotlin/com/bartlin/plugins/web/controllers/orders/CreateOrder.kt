package com.bartlin.plugins.web.controllers.orders

import com.barltin.adapters.drink.toMapper
import com.barltin.adapters.order.CreateOrderInputMapper
import com.barltin.adapters.table.toMapper
import com.bartlin.application.drink.DrinkService
import com.bartlin.application.order.CreateOrderInput
import com.bartlin.application.order.OrderService
import com.bartlin.application.table.TableService
import com.bartlin.plugins.web.CREATE_ORDER
import com.bartlin.plugins.web.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.createOrder(tables: TableService, drinks: DrinkService, orders: OrderService) {
    get {
        val allTables = tables.findAll().map { it.toMapper() }
        val allDrinks = drinks.findAll().map { it.toMapper() }

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
                                for (table in allTables) {
                                    option {
                                        value = table.id
                                        +table.name
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
                                for (drink in allDrinks) {
                                    option {
                                        value = drink.id
                                        +drink.name
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
    return CreateOrderInputMapper(
        tableId = this["table"],
        drinkId = this["drink"],
    ).toCreateOrderInput()
}
