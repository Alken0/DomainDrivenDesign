package com.bartlin.plugins.web.controllers.reservations

import com.barltin.adapters.values.toId
import com.bartlin.application.reservation.ReservationService
import com.bartlin.plugins.web.RESERVATIONS
import com.bartlin.plugins.web.util.getId
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.deleteReservation(service: ReservationService) {
    get {
        val id = call.parameters.getId().toId()
        service.deleteById(id)
        call.respondRedirect(RESERVATIONS)
    }
}
