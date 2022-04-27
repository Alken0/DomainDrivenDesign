package com.bartlin.plugins.web.controllers

import com.barltin.adapters.order.toMapper
import com.barltin.adapters.values.toId
import com.bartlin.application.order.OrderService
import com.bartlin.plugins.web.INDEX
import com.bartlin.plugins.web.templates.BaseTemplate
import com.bartlin.plugins.web.util.getId
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.bill(service: OrderService) {
    get {
        val tableId = call.parameters.getId().toId()
        val bill = service.getBillForTable(tableId).toMapper()

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
                                td { +item.name }
                                td { +"${item.priceInEuro}€" }
                            }
                        }
                    }
                    tfoot {
                        tr {
                            th { +"Total" }
                            th { +"${bill.totalInEuros()}€" }
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
        val tableId = call.parameters.getId().toId()
        service.clearTable(tableId)
        call.respondRedirect(INDEX)
    }
}
