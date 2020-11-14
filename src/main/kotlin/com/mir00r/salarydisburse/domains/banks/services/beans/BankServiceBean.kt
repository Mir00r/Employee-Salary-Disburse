package com.mir00r.salarydisburse.domains.banks.services.beans

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.PageAttr
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.banks.models.entities.Bank
import com.mir00r.salarydisburse.domains.banks.repositories.BankRepository
import com.mir00r.salarydisburse.domains.banks.services.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class BankServiceBean @Autowired constructor(
        private val bankRepository: BankRepository
) : BankService {

    override fun search(query: String, page: Int, size: Int): Page<Bank> {
        return this.bankRepository.search(query, PageAttr.getPageRequest(page, size))
    }

    override fun save(entity: Bank): Bank {
        return this.bankRepository.save(entity)
    }

    override fun find(id: Long): Optional<Bank> {
        return this.bankRepository.find(id)
    }

    override fun findByUUIDIncludingDeleted(uuId: String): Optional<Bank> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long, softDelete: Boolean) {
        if (softDelete) {
            val entity = this.find(id).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK, id) }
            entity.isDeleted = true
            this.bankRepository.save(entity)
        }
        this.bankRepository.deleteById(id)
    }
}
