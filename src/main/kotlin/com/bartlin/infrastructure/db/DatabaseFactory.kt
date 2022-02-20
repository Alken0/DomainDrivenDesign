package com.bartlin.infrastructure.db

import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.OrderTable
import com.bartlin.infrastructure.db.tables.ReservationTable
import com.bartlin.infrastructure.db.tables.TableTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun inMemory() {
        Database.connect(inMemoryConfig())
        setup()
    }

    private fun setup() {
        transaction {
            addLogger(StdOutSqlLogger)
            create(TableTable)
            create(DrinkTable)
            create(OrderTable)
            create(ReservationTable)
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
