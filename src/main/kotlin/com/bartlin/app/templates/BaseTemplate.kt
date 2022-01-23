package com.bartlin.app.templates

import com.bartlin.app.CREATE_DRINK
import com.bartlin.app.GET_DRINKS_MENU
import com.bartlin.app.INDEX
import io.ktor.html.*
import kotlinx.html.*


class BaseTemplate : Template<HTML> {
	val header = Placeholder<FlowContent>()
	val content = Placeholder<FlowContent>()
	override fun HTML.apply() {
		head {
			title("BARTLIN")
			meta("charset", "UTF-8")
		}
		body {
			h1 {
				insert(header)
			}
			insert(content)
			
			br
			footer {
				a(href = INDEX) { +"Home" }
				+" | "
				a(href = CREATE_DRINK) { +"Create Drink" }
				+" | "
				a(href = GET_DRINKS_MENU) { +"Drinks Menu" }
			}
		}
	}
}
