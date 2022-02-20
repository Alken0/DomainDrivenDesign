package com.bartlin.domain.services

import com.bartlin.domain.dto.reservations.ReservationOutput
import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.repositories.ReservationRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Time
import io.ktor.features.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class ReservationServiceTest {
    private val reservations = mockk<ReservationRepository>()
    private val service = ReservationService(reservations)

    @Test
    fun findByIdReturnsCorrectElement() {
        val expected = Reservation(id = Id(12), customer = Name("name"), time = Time.now(), tableId = Id(0))

        every { reservations.findById(expected.id) } returns expected

        val result = service.findById(expected.id)

        assertEquals(ReservationOutput(expected), result)
        verify(exactly = 1) { reservations.findById(expected.id) }
    }

    @Test
    fun findByIdThrowsExceptionIfIdDoesNotExist() {
        val expected = Reservation(id = Id(12), customer = Name("name"), time = Time.now(), tableId = Id(0))

        every { reservations.findById(expected.id) } returns null

        assertThrows<NotFoundException> { service.findById(expected.id) }
        verify(exactly = 1) { reservations.findById(expected.id) }
    }

    @Test
    fun deleteWorks() {
        val id = Id(1)

        every { reservations.deleteById(id) } returns Unit

        reservations.deleteById(id)

        verify(exactly = 1) { reservations.deleteById(id) }
    }
}
