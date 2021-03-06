package com.bartlin.plugins.web.templates

import com.bartlin.plugins.web.CREATE_ORDER
import com.bartlin.plugins.web.MENU
import com.bartlin.plugins.web.RESERVATIONS
import com.bartlin.plugins.web.TABLES
import io.ktor.html.*
import kotlinx.html.*


class BaseTemplate : Template<HTML> {
    val header = Placeholder<FlowContent>()
    val content = Placeholder<FlowContent>()
    override fun HTML.apply() {
        head {
            title("BARTLIN")
            meta("charset", "UTF-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1, shrink-to-fit=no")
            link(
                rel = "stylesheet",
                href = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            )
        }

        body {
            nav("navbar navbar-dark bg-primary navbar-expand-lg px-4 mb-4") {
                a(href = "/", classes = "navbar-brand") {
                    +"BARTLIN"
                }
                div("container-fluid") {
                    ul("navbar-nav") {
                        li("nav-item") {
                            a(href = MENU, classes = "nav-link") { +"Menu" }
                        }
                        li("nav-item") {
                            a(href = CREATE_ORDER, classes = "nav-link") { +"Create Order" }
                        }
                        li("nav-item") {
                            a(href = TABLES, classes = "nav-link") { +"Tables" }
                        }
                        li("nav-item") {
                            a(href = RESERVATIONS, classes = "nav-link") { +"Reservations" }
                        }
                    }
                }

            }

            main {
                div("container") {
                    h2("mb-4") { insert(header) }
                    insert(content)
                }
            }

            script {
                src = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity = "sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            }
        }
    }
}
