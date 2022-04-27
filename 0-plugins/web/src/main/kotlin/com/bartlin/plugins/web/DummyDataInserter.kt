package com.bartlin.plugins.web

import com.bartlin.domain.drink.Drink
import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.reservation.Reservation
import com.bartlin.domain.reservation.ReservationRepository
import com.bartlin.domain.table.Table
import com.bartlin.domain.table.TableRepository
import com.bartlin.domain.types.*

class DummyDataInserter(
    private val drinks: DrinkRepository,
    private val tables: TableRepository,
    private val reservations: ReservationRepository
) {
    fun insert() {
        insertDrinks()
        insertTables()
        insertReservations()
    }

    private fun insertDrinks() {
        drinks.create(
            Drink(
                id = Id(0),
                name = Name("Rum Honey Sour"),
                price = Price(900),
                description = Description("Brugal Especial, Falernum, lime juice, egg white, honey, rosemary")
            )
        )

        drinks.create(
            Drink(
                id = Id(0),
                name = Name("43 Sour"),
                price = Price(850),
                description = Description("Licor 43, lemon juice, orange juice")
            )
        )

        drinks.create(
            Drink(
                id = Id(0),
                name = Name("Wake Up"),
                price = Price(600),
                description = Description("almond syrup, lemon juice, sugar syrup, lime juice, orange juice, passion fruit juice")
            )
        )
    }

    private fun insertTables() {
        for (i in 1..5) {
            tables.create(
                Table(
                    id = Id(0),
                    name = Name("Table $i")
                )
            )
        }
    }

    private fun insertReservations() {
        reservations.create(
            Reservation(
                id = Id(0),
                customer = Name("Customer Number One"),
                time = Time(System.currentTimeMillis()),
                tableId = Id(1)
            )
        )
    }
}
