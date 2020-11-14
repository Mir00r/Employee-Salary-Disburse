package com.mir00r.salarydisburse.domains.bankaccounts.services.beans

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.domains.bankaccounts.models.entities.BankAccount
import com.mir00r.salarydisburse.domains.bankaccounts.repositories.BankAccountRepository
import com.mir00r.salarydisburse.domains.bankaccounts.services.BankAccountService
import com.mir00r.salarydisburse.commons.PageAttr
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class BankAccountServiceBean @Autowired constructor(
        private val bankAccountRepository: BankAccountRepository
) : BankAccountService {

    override fun search(query: String, page: Int, size: Int): Page<BankAccount> {
        return this.bankAccountRepository.search(query, PageAttr.getPageRequest(page, size))
    }

    override fun save(entity: BankAccount): BankAccount {
        return this.bankAccountRepository.save(entity)
    }

    override fun find(id: Long): Optional<BankAccount> {
        return this.bankAccountRepository.find(id)
    }

    override fun findByUUIDIncludingDeleted(uuId: String): Optional<BankAccount> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long, softDelete: Boolean) {
        if (softDelete) {
            val entity = this.find(id).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK_ACCOUNT, id) }
            entity.isDeleted = true
            this.bankAccountRepository.save(entity)
        }
        this.bankAccountRepository.deleteById(id)
    }

}
