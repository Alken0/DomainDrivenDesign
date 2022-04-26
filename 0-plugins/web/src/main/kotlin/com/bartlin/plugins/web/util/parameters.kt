package com.bartlin.plugins.web.util

import io.ktor.http.*

fun Parameters.getId(): String {
    return this["id"]!!
}
