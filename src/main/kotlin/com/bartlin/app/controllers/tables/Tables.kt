package com.bartlin.app.controllers.tables

import com.bartlin.app.BILL
import com.bartlin.app.UPDATE_TABLE
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.services.TableService
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.tables(service: TableService) {
    get {
        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Tables"
            }
            content {
                table("table") {
                    thead {
                        tr {
                            th { scope = ThScope.col; +"Name" }
                            th { scope = ThScope.col;+"" }
                            th { scope = ThScope.col;+"" }
                        }
                    }
                    tbody {
                        for (item in service.findAll()) {
                            tr {
                                th { scope = ThScope.row; +item.name.toString() }
                                td {
                                    a(href = UPDATE_TABLE.replace("{id}", item.id.toString())) {
                                        button(classes = "btn btn-secondary") {
                                            +"Edit"
                                        }
                                    }
                                }
                                td {
                                    a(href = BILL.replace("{id}", item.id.toString())) {
                                        button(classes = "btn btn-secondary") {
                                            +"Show Bill"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}