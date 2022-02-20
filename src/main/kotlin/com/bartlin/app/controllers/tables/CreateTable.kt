package com.bartlin.app.controllers.tables

import com.bartlin.app.CREATE_TABLE
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.tables.CreateTableInput
import com.bartlin.domain.services.TableService
import com.bartlin.domain.vo.toName
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
    return CreateTableInput(
        name = this["name"]!!.toName(),
    )
}

