package com.bartlin.plugins.persistence

import com.bartlin.plugins.persistence.drink.DrinkTable
import com.bartlin.plugins.persistence.order.OrderTable
import com.bartlin.plugins.persistence.reservation.ReservationTable
import com.bartlin.plugins.persistence.table.TableTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConnector {
    fun inMemory(): RepositoryFactory {
        Database.connect(inMemoryConfig())
        setup()
        return RepositoryFactory()
    }

    private fun setup() {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(TableTable)
            SchemaUtils.create(DrinkTable)
            SchemaUtils.create(OrderTable)
            SchemaUtils.create(ReservationTable)
        }
    }

    private fun inMemoryConfig(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}
