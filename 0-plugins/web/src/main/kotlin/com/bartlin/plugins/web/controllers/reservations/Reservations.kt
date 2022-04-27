package com.bartlin.plugins.web.controllers.reservations

import com.barltin.adapters.reservation.toMapper
import com.bartlin.application.reservation.ReservationService
import com.bartlin.plugins.web.CREATE_RESERVATION
import com.bartlin.plugins.web.DELETE_RESERVATION
import com.bartlin.plugins.web.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.reservations(service: ReservationService) {
    get {
        val allReservations = service.findAll().map { it.toMapper() }

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
                            th { scope = ThScope.col; +"" }
                        }
                    }
                    tbody {
                        for (reservation in allReservations) {
                            tr {
                                th { scope = ThScope.row; +reservation.customer }
                                th { scope = ThScope.row; +reservation.tableName }
                                th { scope = ThScope.row; +reservation.time }
                                td {
                                    a(href = DELETE_RESERVATION.replace("{id}", reservation.id)) {
                                        button(classes = "btn btn-secondary") {
                                            +"Delete"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                a(href = CREATE_RESERVATION, classes = "btn btn-primary w-100") {
                    +"Create Reservation"
                }
            }
        }
    }
}
