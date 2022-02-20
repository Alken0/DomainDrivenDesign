package com.bartlin.app.controllers.tables

import com.bartlin.app.MENU
import com.bartlin.app.templates.BaseTemplate
import com.bartlin.domain.dto.tables.UpdateTableInput
import com.bartlin.domain.services.TableService
import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.toId
import com.bartlin.domain.vo.toName
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.updateTable(service: TableService) {
    get {
        val id = call.parameters["id"]!!.toId()
        val original = service.findById(id)
        call.respondHtmlTemplate(BaseTemplate()) {
            header {
                +"Update Drink Data"
            }
            content {
                form(method = FormMethod.post) {
                    div("row") {
                        label("form-label") {
                            +"Name"
                            textInput(name = "name", classes = "form-control") {
                                value = original.name.toString()
                            }
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
        val id = call.parameters["id"]!!.toId()
        val data = call.receiveParameters().toUpdateTable(id)
        service.update(data)
        call.respondRedirect(MENU)
    }
}

private fun Parameters.toUpdateTable(id: Id): UpdateTableInput {
    return UpdateTableInput(
        id = id,
        name = this["name"]?.ifBlank { null }?.toName()
    )
}
