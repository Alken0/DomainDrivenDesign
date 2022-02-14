package com.bartlin.app.controllers

import com.bartlin.app.templates.BaseTemplate
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.div

fun Route.index() {
	get {
		call.respondHtmlTemplate(BaseTemplate()) {
			header {
				+"BARTLIN a Hipster Cocktailbar"
			}
			content {
				div {
					+"Please use the header for navigation!"
				}
			}
		}
	}
}
