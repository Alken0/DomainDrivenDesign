package com.bartlin.application.table

import com.bartlin.domain.types.Id
import com.bartlin.domain.types.Name

data class UpdateTableInput(
    val id: Id,
    val name: Name?
)
