package com.mir00r.salarydisburse.domains.companys.services.beans

import com.mir00r.salarydisburse.domains.companys.models.entities.Company
import com.mir00r.salarydisburse.domains.companys.repositories.CompanyRepository
import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.PageAttr
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.bankaccounts.repositories.BankAccountRepository
import com.mir00r.salarydisburse.domains.companys.services.CompanyService
import com.mir00r.salarydisburse.domains.employees.models.entities.Employee
import com.mir00r.salarydisburse.domains.employees.repositories.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyServiceBean @Autowired constructor(
        private val companyRepository: CompanyRepository,
        private val employeeRepository: EmployeeRepository,
        private val bankaccountRepository: BankAccountRepository
) : CompanyService {

    override fun search(query: String, page: Int, size: Int, bankAccountId: Long?): Page<Company> {
        return this.companyRepository.search(query, PageAttr.getPageRequest(page, size), bankAccountId)
    }

    override fun search(query: String, page: Int, size: Int): Page<Company> {
        return this.companyRepository.search(query, PageAttr.getPageRequest(page, size))
    }

    override fun salaryTransfer(companyId: Long, amount: Double): Company {
        val company = this.companyRepository.find(companyId).orElseThrow { ExceptionUtil.getNotFound(Constants.COMPANY, companyId) }
        val companyBankAccount = this.bankaccountRepository.find(company.bankAccount.id).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK_ACCOUNT, company.bankAccount.id) }

        if (company.bankAccount.currentBalance < amount) throw ExceptionUtil.getInvalidException("Company has not sufficient balance to transfer the employee salary")
        val employees = this.employeeRepository.findByCompany(company.id)
        if (employees.isEmpty()) throw ExceptionUtil.getNotFoundMessage("Employee not found to transfer the money")

        val salaryAmount = amount / employees.size
        for (e in employees) {
            val bankAccount = this.bankaccountRepository.find(e.bankAccount.id)
            if (bankAccount.isPresent) {
                val ba = bankAccount.get()
                ba.currentBalance += salaryAmount
                this.bankaccountRepository.save(ba)
            }
        }
        companyBankAccount.currentBalance -= amount
        company.bankAccount = this.bankaccountRepository.save(companyBankAccount)
        company.totalPaidSalary += amount
        return this.companyRepository.save(company)
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
