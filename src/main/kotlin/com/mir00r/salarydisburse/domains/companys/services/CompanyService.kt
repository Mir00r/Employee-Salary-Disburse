package com.mir00r.salarydisburse.domains.companys.services

import com.mir00r.salarydisburse.domains.common.services.CrudService
import com.mir00r.salarydisburse.domains.companys.models.entities.Company
import org.springframework.data.domain.Page

interface CompanyService : CrudService<Company> {
    fun search(query: String, page: Int, size: Int, bankAccountId: Long?): Page<Company>
    fun salaryTransfer(companyId: Long, amount: Double): Company
}
