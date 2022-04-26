package com.bartlin.plugins.web.controllers.tables

import com.barltin.adapters.table.UpdateTableInputMapper
import com.barltin.adapters.table.toMapper
import com.barltin.adapters.values.toId
import com.bartlin.application.table.TableService
import com.bartlin.application.table.UpdateTableInput
import com.bartlin.plugins.web.MENU
import com.bartlin.plugins.web.templates.BaseTemplate
import com.bartlin.plugins.web.util.getId
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.updateTable(service: TableService) {
    get {
        val id = call.parameters.getId().toId()
        val original = service.findById(id).toMapper()

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
                                value = original.name
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
        val id = call.parameters.getId()
        val data = call.receiveParameters().toUpdateTable(id)
        service.update(data)
        call.respondRedirect(MENU)
    }
}

private fun Parameters.toUpdateTable(id: String?): UpdateTableInput {
    return UpdateTableInputMapper(
        id = id,
        name = this["name"]
    ).toUpdateTableInput()
}
