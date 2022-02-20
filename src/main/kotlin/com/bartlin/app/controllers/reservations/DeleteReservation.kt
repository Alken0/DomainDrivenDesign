package com.bartlin.app.controllers.reservations

import com.bartlin.app.RESERVATIONS
import com.bartlin.domain.services.ReservationService
import com.bartlin.domain.vo.toId
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deleteReservation(service: ReservationService) {
    get {
        val id = call.parameters["id"]!!.toId()
        service.deleteById(id)
        call.respondRedirect(RESERVATIONS)
    }
}
