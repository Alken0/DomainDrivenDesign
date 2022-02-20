package com.bartlin.app.controllers.reservations

import com.bartlin.app.DELETE_RESERVATION
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.ReservationService
import com.bartlin.domain.services.TableService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.reservations(service: ReservationService, tables: TableService) {
    get {
        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Reservations"
            }
            content {
                table("table") {
                    thead {
                        tr {
                            th { scope = ThScope.col; +"Name" }
                            th { scope = ThScope.col; +"Table" }
                            th { scope = ThScope.col; +"Time" }
                            th { scope = ThScope.col;+"" }
                        }
                    }
                    tbody {
                        for (item in service.findAll()) {
                            tr {
                                th { scope = ThScope.row; +item.customer.toString() }
                                th { scope = ThScope.row; +tables.findById(item.tableId).name.toString() }
                                th { scope = ThScope.row; +item.time.toString() }
                                td {
                                    a(href = DELETE_RESERVATION.replace("{id}", item.id.toString())) {
                                        button(classes = "btn btn-secondary") {
                                            +"Delete"
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
