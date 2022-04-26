package com.bartlin.plugins.web.controllers.reservations

import com.barltin.adapters.reservation.CreateReservationInputMapper
import com.barltin.adapters.table.toMapper
import com.bartlin.application.reservation.CreateReservationInput
import com.bartlin.application.reservation.ReservationService
import com.bartlin.application.table.TableService
import com.bartlin.plugins.web.CREATE_RESERVATION
import com.bartlin.plugins.web.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.createReservation(reservations: ReservationService, tables: TableService) {
    get {
        val allTables = tables.findAll().map { it.toMapper() }

        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Create a new Reservation"
            }
            content {
                form(method = FormMethod.post) {
                    div("row") {
                        label("form-label") {
                            +"Customer Name"
                            textInput(name = "customer", classes = "form-control") { required = true }
                        }
                    }

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
                            +"Time"
                            dateTimeLocalInput(name = "time") {
                                placeholder = "yyyy-MM-dd hh:mm:ss"
                                required = true
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
        val data = call.receiveParameters().toCreateReservationInput()
        reservations.create(data)
        call.respondRedirect(CREATE_RESERVATION)
    }
}

private fun Parameters.toCreateReservationInput(): CreateReservationInput {
    return CreateReservationInputMapper(
        customer = this["customer"],
        time = this["time"],
        tableId = this["table"]
    ).toCreateReservationInput()
}

