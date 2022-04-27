package com.barltin.adapters.values

import com.bartlin.domain.types.Id

fun String.toId(): Id {
    return Id(this.toLong())
}
