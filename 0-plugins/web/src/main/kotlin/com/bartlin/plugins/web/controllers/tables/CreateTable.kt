package com.bartlin.plugins.web.controllers.tables

import com.barltin.adapters.table.CreateTableInputMapper
import com.bartlin.application.table.CreateTableInput
import com.bartlin.application.table.TableService
import com.bartlin.plugins.web.CREATE_TABLE
import com.bartlin.plugins.web.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.createTable(service: TableService) {
    get {
        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Create a new Table"
            }
            content {
                form(method = FormMethod.post) {
                    div("row") {
                        label("form-label") {
                            +"Name"
                            textInput(name = "name", classes = "form-control") { required = true }
                        }
                    }

                    div("row mx-auto") {
                        button(classes = "btn btn-primary") {
                            type = ButtonType.submit
                            +"Submit"
                        }
                    }
                }
            }
        }
    }

    post {
        val data = call.receiveParameters().toCreateTableInput()
        service.create(data)
        call.respondRedirect(CREATE_TABLE)
    }
}

private fun Parameters.toCreateTableInput(): CreateTableInput {
    return CreateTableInputMapper(
        name = this["name"],
    ).toCreateTableInput()
}

