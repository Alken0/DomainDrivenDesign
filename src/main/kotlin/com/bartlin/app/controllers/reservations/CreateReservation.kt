package com.bartlin.app.controllers.reservations

import com.bartlin.app.CREATE_RESERVATION
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.reservations.CreateReservationInput
import com.bartlin.domain.services.ReservationService
import com.bartlin.domain.services.TableService
import com.bartlin.domain.vo.Time
import com.bartlin.domain.vo.toId
import com.bartlin.domain.vo.toName
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.createReservation(reservations: ReservationService, tables: TableService) {
    get {
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
    return CreateReservationInput(
        customer = this["customer"]!!.toName(),
        time = Time.parse(this["time"]!!),
        tableId = this["table"]!!.toId()
    )
}

