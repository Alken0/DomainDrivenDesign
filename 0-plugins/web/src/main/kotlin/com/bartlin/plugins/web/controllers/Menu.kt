package com.bartlin.plugins.web.controllers

import com.barltin.adapters.drink.toMapper
import com.bartlin.application.drink.DrinkService
import com.bartlin.plugins.web.CREATE_DRINK
import com.bartlin.plugins.web.UPDATE_DRINK
import com.bartlin.plugins.web.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.getMenu(service: DrinkService) {
    get {
        val allDrinks = service.findAll().map { it.toMapper() }

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
                        for (item in allDrinks) {
                            tr {
                                th { scope = ThScope.row; +item.id }
                                td { +item.name }
                                td { +"${item.priceInEuros}€" }
                                td { +item.description }
                                td {
                                    a(href = UPDATE_DRINK.replace("{id}", item.id)) {
                                        button(classes = "btn btn-secondary") {
                                            +"edit"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                a(href = CREATE_DRINK, classes = "btn btn-primary w-100") { +"Create Drink" }
            }
        }
    }
}
