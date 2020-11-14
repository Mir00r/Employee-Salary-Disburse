package com.mir00r.salarydisburse.domains.common.services

interface IDeleteService {
    fun delete(id: Long)
    fun softDelete(id: Long)
}
