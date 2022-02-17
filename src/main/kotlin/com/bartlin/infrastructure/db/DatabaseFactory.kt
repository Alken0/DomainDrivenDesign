package com.bartlin.infrastructure.db

import com.bartlin.infrastructure.db.tables.DrinkTable
import com.bartlin.infrastructure.db.tables.OrderTable
import com.bartlin.infrastructure.db.tables.TableTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
	fun init() {
		Database.connect(hikari())
		
		transaction {
			addLogger(StdOutSqlLogger)
			create(TableTable)
			create(DrinkTable)
			create(OrderTable)
			dummyTables()
			dummyDrinks()
		}
	}

	private fun dummyDrinks() {
		transaction {
			DrinkTable.insert {
				it[this.name] = "Rum Honey Sour"
				it[this.price] = 900
				it[this.description] = "Brugal Especial, Falernum, lime juice, egg white, honey, rosemary"
			}
			DrinkTable.insert {
				it[this.name] = "43 Sour"
				it[this.price] = 850
				it[this.description] = "Licor 43, lemon juice, orange juice"
			}
			DrinkTable.insert {
				it[this.name] = "Wake Up"
				it[this.price] = 600
				it[this.description] =
					"almond syrup, lemon juice, sugar syrup, lime juice, orange juice, passion fruit juice"
			}
		}
	}

	private fun dummyTables() {
		transaction {
			for (i in 0..9) {
				TableTable.insert { }
			}
		}
	}

	private fun hikari(): HikariDataSource {
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
