package com.bartlin.application.reservation

import com.bartlin.domain.reservation.Reservation
import com.bartlin.domain.reservation.ReservationRepository
import com.bartlin.domain.reservation.ReservationWithTableName
import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name
import com.bartlin.domain.types.Time
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
        val expected = ReservationWithTableName(
            id = Id(12),
            customer = Name("name"),
            time = Time(System.currentTimeMillis()),
            tableName = Name("table-name")
        )

        every { reservations.findByIdWithTableName(expected.id) } returns expected

        val result = service.findById(expected.id)

        assertEquals(ReservationOutput(expected), result)
        verify(exactly = 1) { reservations.findByIdWithTableName(expected.id) }
    }

    @Test
    fun findByIdThrowsExceptionIfIdDoesNotExist() {
        val expected =
            Reservation(id = Id(12), customer = Name("name"), time = Time(System.currentTimeMillis()), tableId = Id(0))

        every { reservations.findByIdWithTableName(expected.id) } returns null

        assertThrows<NoSuchElementException> { service.findById(expected.id) }
        verify(exactly = 1) { reservations.findByIdWithTableName(expected.id) }
    }

    @Test
    fun deleteWorks() {
        val id = Id(1)

        every { reservations.deleteById(id) } returns Unit

        reservations.deleteById(id)

        verify(exactly = 1) { reservations.deleteById(id) }
    }
}
