package com.mir00r.salarydisburse.domains.companys.services.beans

import com.mir00r.salarydisburse.domains.companys.models.entities.Company
import com.mir00r.salarydisburse.domains.companys.repositories.CompanyRepository
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.PageAttr
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.companys.services.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyServiceBean @Autowired constructor(
        private val companyRepository: CompanyRepository
) : CompanyService {

    override fun search(query: String, page: Int, size: Int, bankAccountId: Long?): Page<Company> {
        return this.companyRepository.search(query, PageAttr.getPageRequest(page, size), bankAccountId)
    }

    override fun search(query: String, page: Int, size: Int): Page<Company> {
        return this.companyRepository.search(query, PageAttr.getPageRequest(page, size))
    }

    override fun save(entity: Company): Company {
        return this.companyRepository.save(entity)
    }

    override fun find(id: Long): Optional<Company> {
        return this.companyRepository.find(id)
    }

    override fun findByUUIDIncludingDeleted(uuId: String): Optional<Company> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long, softDelete: Boolean) {
        if (softDelete) {
            val entity = this.find(id).orElseThrow { ExceptionUtil.getNotFound(Constants.COMPANY, id) }
            entity.isDeleted = true
            this.companyRepository.save(entity)
        }
        this.companyRepository.deleteById(id)
    }
}
