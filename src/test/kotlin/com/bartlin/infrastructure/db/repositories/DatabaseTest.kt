package com.bartlin.infrastructure.db.repositories

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random.Default.nextInt

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class DatabaseTest(private val tables: Array<Table>) {

	private val databaseName: String = "testDatabase${nextInt(0, 999999)}"
	private val database = Database.connect("jdbc:h2:mem:$databaseName;DB_CLOSE_DELAY=-1;IGNORECASE=true;")

	@BeforeEach
	private fun databaseSetUp() {
		transaction(database) {
			SchemaUtils.drop(*tables)
			SchemaUtils.create(*tables)
		}
	}

	@AfterAll
	private fun databaseTearDown() {
		TransactionManager.closeAndUnregister(database)
	}
}
