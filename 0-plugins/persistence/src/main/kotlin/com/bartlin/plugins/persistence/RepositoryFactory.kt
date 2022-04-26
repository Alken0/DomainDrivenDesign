package com.bartlin.plugins.persistence

import com.bartlin.domain.drink.DrinkRepository
import com.bartlin.domain.order.OrderRepository
import com.bartlin.domain.reservation.ReservationRepository
import com.bartlin.domain.table.TableRepository
import com.bartlin.plugins.persistence.drink.DrinkRepositoryExposed
import com.bartlin.plugins.persistence.order.OrderRepositoryExposed
import com.bartlin.plugins.persistence.reservation.ReservationRepositoryExposed
import com.bartlin.plugins.persistence.table.TableRepositoryExposed


class RepositoryFactory internal constructor() {
    fun getDrinkRepository(): DrinkRepository {
        return DrinkRepositoryExposed()
    }

    fun getOrderRepository(): OrderRepository {
        return OrderRepositoryExposed()
    }

    fun getReservationRepository(): ReservationRepository {
        return ReservationRepositoryExposed()
    }

    fun getTableRepository(): TableRepository {
        return TableRepositoryExposed()
    }
}
