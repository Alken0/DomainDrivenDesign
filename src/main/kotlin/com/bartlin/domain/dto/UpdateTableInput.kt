package com.bartlin.domain.dto

import com.bartlin.domain.vo.Id
import com.bartlin.domain.vo.Name

data class UpdateTableInput(
    val id: Id,
    val name: Name?
)
