package com.bartlin.app

import com.bartlin.domain.entities.Drink
import com.bartlin.domain.entities.Reservation
import com.bartlin.domain.entities.Table
import com.bartlin.domain.repositories.DrinkRepository
import com.bartlin.domain.repositories.ReservationRepository
import com.bartlin.domain.repositories.TableRepository
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name
import com.bartlin.domain.vo.Price
import com.bartlin.domain.vo.Time

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
                description = "Brugal Especial, Falernum, lime juice, egg white, honey, rosemary"
            )
        )

        drinks.create(
            Drink(
                id = Id(0),
                name = Name("43 Sour"),
                price = Price(850),
                description = "Licor 43, lemon juice, orange juice"
            )
        )

        drinks.create(
            Drink(
                id = Id(0),
                name = Name("Wake Up"),
                price = Price(600),
                description = "almond syrup, lemon juice, sugar syrup, lime juice, orange juice, passion fruit juice"
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
                time = Time.now(),
                tableId = Id(1)
            )
        )
    }
}
