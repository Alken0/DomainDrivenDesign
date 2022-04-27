package com.bartlin.plugins.web.controllers.tables

import com.barltin.adapters.table.toMapper
import com.bartlin.application.table.TableService
import com.bartlin.plugins.web.BILL
import com.bartlin.plugins.web.CREATE_TABLE
import com.bartlin.plugins.web.UPDATE_TABLE
import com.bartlin.plugins.web.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.tables(service: TableService) {
    get {
        val allTables = service.findAll().map { it.toMapper() }

        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Tables"
            }
            content {
                table("table") {
                    thead {
                        tr {
                            th { scope = ThScope.col; +"Name" }
                            th { scope = ThScope.col; +"" }
                            th { scope = ThScope.col; +"" }
                        }
                    }
                    tbody {
                        for (table in allTables) {
                            tr {
                                th { scope = ThScope.row; +table.name }
                                td {
                                    a(href = UPDATE_TABLE.replace("{id}", table.id)) {
                                        button(classes = "btn btn-secondary") {
                                            +"Edit"
                                        }
                                    }
                                }
                                td {
                                    a(href = BILL.replace("{id}", table.id)) {
                                        button(classes = "btn btn-secondary") {
                                            +"Show Bill"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                a(href = CREATE_TABLE, classes = "btn btn-primary w-100") { +"Create Table" }
            }
        }
    }
}
