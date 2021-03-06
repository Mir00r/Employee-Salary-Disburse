package com.mir00r.salarydisburse.domains.companys.models.mappers

import com.mir00r.salarydisburse.commons.Constants
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.bankaccounts.models.mappers.BankAccountMapper
import com.mir00r.salarydisburse.domains.bankaccounts.services.BankAccountService
import com.mir00r.salarydisburse.domains.companys.models.dtos.CompanyDto
import com.mir00r.salarydisburse.domains.companys.models.entities.Company
import com.mir00r.salarydisburse.domains.common.models.mapper.BaseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CompanyMapper @Autowired constructor(
        private val bankAccountService: BankAccountService,
        private val bankAccountMapper: BankAccountMapper
) : BaseMapper<Company, CompanyDto> {

    override fun map(entity: Company): CompanyDto {
        val dto = CompanyDto()
        dto.apply {
            id = entity.id
            created = entity.created
            uuid = entity.uuid
            lastUpdated = entity.lastUpdated

            name = entity.name
            phone = entity.phone
            address = entity.address
            totalPaidSalary = entity.totalPaidSalary
            bankAccountId = entity.bankAccount.id
            bankAccountInfo = bankAccountMapper.map(entity.bankAccount)
        }

        return dto
    }

    override fun map(dto: CompanyDto, exEntity: Company?): Company {
        val entity = exEntity ?: Company()
        entity.apply {
            bankAccount = bankAccountService.find(dto.bankAccountId).orElseThrow { ExceptionUtil.getNotFound(Constants.BANK_ACCOUNT, dto.bankAccountId) }
            name = dto.name
            phone = dto.phone
            address = dto.address
            totalPaidSalary = dto.totalPaidSalary
        }
        return entity
    }
}
