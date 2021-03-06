package com.mir00r.salarydisburse.domains.common.models.mapper

interface BaseMapper<T, S> {
    fun map(entity: T): S
    fun map(dto: S, entity: T?): T
}
